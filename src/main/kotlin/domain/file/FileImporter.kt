package domain.file

import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.models.BankAccount
import domain.models.Category
import domain.models.Operation
import domain.models.common.toId
import java.io.File

abstract class FileImporter(
    protected val bankAccountFacade: BankAccountFacade,
    protected val categoryFacade: CategoryFacade,
    protected val operationFacade: OperationFacade
) {

    // Шаблонный метод
    fun importData(config: ImportConfig) {
        // 1. Импорт категорий
        config.categoriesFile?.let { filePath ->
            val content = readFileContent(filePath)
            if (content != null) {
                val categories = parseCategories(content)
                categories.forEach { categoryFacade.addExistingCategory(it) }
            }
        }

        // 2. Импорт банковских счетов
        config.bankAccountsFile?.let { filePath ->
            val content = readFileContent(filePath)
                if (content != null) {
                val accounts = parseBankAccounts(content)
                accounts.forEach { bankAccountFacade.addExistingBankAccount(it) }
            }
        }

        // 3. Импорт операций (только если есть связанные сущности)
        config.operationsFile?.let { filePath ->
            val content = readFileContent(filePath)
            if (content != null) {
                val operations = parseOperations(content)
                operations.forEach { operation ->
                    val accountExists = bankAccountFacade.getBankAccountById(operation.bankAccountId.toId()) != null
                    val categoryExists = categoryFacade.getCategoryById(operation.categoryId.toId()) != null

                    if (accountExists && categoryExists) {
                        operationFacade.addExistingOperation(operation)
                    }
                }
            }
        }
    }

    private fun readFileContent(filePath: String): String? {
        val file = File(filePath)
        return if (file.exists() && file.isFile) {
            file.readText()
        } else {
            null
        }
    }

    abstract fun parseCategories(content: String): List<Category>
    abstract fun parseBankAccounts(content: String): List<BankAccount>
    abstract fun parseOperations(content: String): List<Operation>
}