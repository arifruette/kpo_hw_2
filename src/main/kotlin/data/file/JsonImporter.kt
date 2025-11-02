package data.file

import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.file.FileImporter
import domain.models.BankAccount
import domain.models.Category
import domain.models.Operation
import domain.serializable.SerializableBankAccount
import domain.serializable.SerializableCategory
import domain.serializable.SerializableOperation
import kotlinx.serialization.json.Json
import javax.inject.Inject

class JsonImporter @Inject constructor(
    bankAccountFacade: BankAccountFacade,
    categoryFacade: CategoryFacade,
    operationFacade: OperationFacade
) : FileImporter(bankAccountFacade, categoryFacade, operationFacade) {

    private val json = Json { ignoreUnknownKeys = true }

    override fun parseCategories(content: String): List<Category> {
        return try {
            json.decodeFromString<List<SerializableCategory>>(content).map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun parseBankAccounts(content: String): List<BankAccount> {
        return try {
            json.decodeFromString<List<SerializableBankAccount>>(content).map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun parseOperations(content: String): List<Operation> {
        return try {
            json.decodeFromString<List<SerializableOperation>>(content).map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}