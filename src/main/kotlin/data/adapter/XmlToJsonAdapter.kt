package data.adapter
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.ByteArrayInputStream
import javax.xml.parsers.DocumentBuilderFactory

class XmlToJsonAdapter {

    fun convertXmlToJson(xmlContent: String): String {
        val document = parseXml(xmlContent)
        return when (document.documentElement.nodeName) {
            "BankAccounts" -> convertBankAccounts(document)
            "Categories" -> convertCategories(document)
            "Operations" -> convertOperations(document)
            else -> throw IllegalArgumentException("Unknown XML root element: ${document.documentElement.nodeName}")
        }
    }

    private fun parseXml(xmlContent: String): Document {
        val factory = DocumentBuilderFactory.newInstance()
        val builder = factory.newDocumentBuilder()
        return builder.parse(ByteArrayInputStream(xmlContent.toByteArray(Charsets.UTF_8)))
    }

    private fun convertBankAccounts(document: Document): String {
        val accounts = document.getElementsByTagName("BankAccount")
        val jsonArray = StringBuilder("[")

        for (i in 0 until accounts.length) {
            val account = accounts.item(i) as Element
            if (i > 0) jsonArray.append(",")
            jsonArray.append("""
            {
                "id": "${escapeJson(getTextContent(account, "id"))}",
                "name": "${escapeJson(getTextContent(account, "name"))}",
                "balance": ${getTextContent(account, "balance")}
            }
            """.trimIndent())
        }

        jsonArray.append("]")
        return jsonArray.toString()
    }

    private fun convertCategories(document: Document): String {
        val categories = document.getElementsByTagName("Category")
        val jsonArray = StringBuilder("[")

        for (i in 0 until categories.length) {
            val category = categories.item(i) as Element
            if (i > 0) jsonArray.append(",")
            jsonArray.append("""
            {
                "id": "${escapeJson(getTextContent(category, "id"))}",
                "type": "${escapeJson(getTextContent(category, "type"))}",
                "name": "${escapeJson(getTextContent(category, "name"))}"
            }
            """.trimIndent())
        }

        jsonArray.append("]")
        return jsonArray.toString()
    }

    private fun convertOperations(document: Document): String {
        val operations = document.getElementsByTagName("Operation")
        val jsonArray = StringBuilder("[")

        for (i in 0 until operations.length) {
            val operation = operations.item(i) as Element
            if (i > 0) jsonArray.append(",")

            val description = getTextContent(operation, "description")
            jsonArray.append("""
            {
                "id": "${escapeJson(getTextContent(operation, "id"))}",
                "type": "${escapeJson(getTextContent(operation, "type"))}",
                "bankAccountId": "${escapeJson(getTextContent(operation, "bankAccountId"))}",
                "amount": ${getTextContent(operation, "amount")},
                "date": "${escapeJson(getTextContent(operation, "date"))}",
                "description": ${if (description.isNotEmpty()) "\"${escapeJson(description)}\"" else "null"},
                "categoryId": "${escapeJson(getTextContent(operation, "categoryId"))}"
            }
            """.trimIndent())
        }

        jsonArray.append("]")
        return jsonArray.toString()
    }

    private fun getTextContent(element: Element, tagName: String): String {
        val nodes = element.getElementsByTagName(tagName)
        return if (nodes.length > 0) nodes.item(0).textContent else ""
    }

    private fun escapeJson(value: String): String {
        return value.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
    }
}