package data.visitor

import domain.models.BankAccount
import domain.models.Category
import domain.models.Operation
import domain.visitor.Visitor
import javax.inject.Inject

// JSON Visitor
class JsonVisitorImpl @Inject constructor(): Visitor {
    override fun processBankAccounts(bankAccounts: List<BankAccount>): String {
        return bankAccounts.joinToString(
            separator = ",\n    ",
            prefix = "[\n    ",
            postfix = "\n]"
        ) { account ->
            """
            {
                "id": "${escapeJson(account.id.raw)}",
                "name": "${escapeJson(account.name)}",
                "balance": ${account.balance}
            }
            """.trimIndent()
        }
    }

    override fun processCategories(categories: List<Category>): String {
        return categories.joinToString(
            separator = ",\n    ",
            prefix = "[\n    ",
            postfix = "\n]"
        ) { category ->
            """
            {
                "id": "${escapeJson(category.id.raw)}",
                "type": "${category.type}",
                "name": "${escapeJson(category.name)}"
            }
            """.trimIndent()
        }
    }

    override fun processOperations(operations: List<Operation>): String {
        return operations.joinToString(
            separator = ",\n    ",
            prefix = "[\n    ",
            postfix = "\n]"
        ) { operation ->
            """
            {
                "id": "${escapeJson(operation.id.raw)}",
                "type": "${operation.type}",
                "bankAccountId": "${escapeJson(operation.bankAccountId)}",
                "amount": ${operation.amount},
                "date": "${operation.date}",
                "description": ${operation.description?.let { "\"${escapeJson(it)}\"" } ?: "null"},
                "categoryId": "${escapeJson(operation.categoryId)}"
            }
            """.trimIndent()
        }
    }

    private fun escapeJson(value: String): String {
        return value.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
    }
}