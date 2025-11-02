package data.visitor
import domain.models.BankAccount
import domain.models.Category
import domain.models.Operation
import domain.visitor.Visitor
import javax.inject.Inject

// CSV Visitor
class CsvVisitorImpl @Inject constructor(): Visitor {
    override fun processBankAccounts(bankAccounts: List<BankAccount>): String {
        if (bankAccounts.isEmpty()) return ""

        val header = "id,name,balance\n"
        val rows = bankAccounts.joinToString("\n") { account ->
            "${escapeCsvField(account.id.raw)},${escapeCsvField(account.name)},${account.balance}"
        }
        return header + rows
    }

    override fun processCategories(categories: List<Category>): String {
        if (categories.isEmpty()) return ""

        val header = "id,type,name\n"
        val rows = categories.joinToString("\n") { category ->
            "${escapeCsvField(category.id.raw)},${category.type},${escapeCsvField(category.name)}"
        }
        return header + rows
    }

    override fun processOperations(operations: List<Operation>): String {
        if (operations.isEmpty()) return ""

        val header = "id,type,bankAccountId,amount,date,description,categoryId\n"
        val rows = operations.joinToString("\n") { operation ->
            "${escapeCsvField(operation.id.raw)},${operation.type},${escapeCsvField(operation.bankAccountId)}," +
                    "${operation.amount},${operation.date},${escapeCsvField(operation.description ?: "")}," +
                    escapeCsvField(operation.categoryId)
        }
        return header + rows
    }

    private fun escapeCsvField(field: String): String {
        return if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            "\"${field.replace("\"", "\"\"")}\""
        } else {
            field
        }
    }
}