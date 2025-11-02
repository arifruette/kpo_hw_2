package data.facade

import domain.facade.OperationFacade
import domain.factory.OperationFactory
import domain.models.BankAccount
import domain.models.CategoryType
import domain.models.Operation
import domain.models.OperationType
import domain.models.common.Id
import domain.repository.BankAccountRepository
import domain.repository.CategoryRepository
import domain.repository.OperationRepository
import domain.visitor.Visitor
import java.time.LocalDateTime
import javax.inject.Inject

class OperationFacadeImpl @Inject constructor(
    private val operationRepository: OperationRepository,
    private val bankAccountRepository: BankAccountRepository,
    private val categoryRepository: CategoryRepository,
    private val operationFactory: OperationFactory
) : OperationFacade {

    override fun addOperation(
        type: OperationType,
        bankAccountId: String,
        amount: Double,
        date: LocalDateTime,
        description: String?,
        categoryId: String
    ): Boolean {
        try {
            val operation = operationFactory.createOperation(
                type = type,
                bankAccountId = bankAccountId,
                amount = amount,
                date = date,
                description = description,
                categoryId = categoryId
            )

            val account = bankAccountRepository.findById(Id(bankAccountId)) ?: return false
            val category = categoryRepository.findById(Id(categoryId)) ?: return false

            if (!isTypeMatching(type, category.type)) return false

            val saved = operationRepository.save(operation)
            if (saved) updateAccountBalance(account, amount, type)
            return saved
        } catch (e: IllegalArgumentException) {
            return false
        }
    }

    override fun getOperationById(id: Id): Operation? {
        return operationRepository.findById(id)
    }

    override fun updateOperationById(
        id: Id,
        type: OperationType?,
        bankAccountId: String?,
        amount: Double?,
        date: LocalDateTime?,
        description: String?,
        categoryId: String?
    ): Boolean {
        val existing = operationRepository.findById(id) ?: return false

        val oldAccount = bankAccountRepository.findById(Id(existing.bankAccountId))
        val oldAmount = existing.amount
        val oldType = existing.type

        val newAccount = bankAccountId?.let { bankAccountRepository.findById(Id(it)) }
        if (bankAccountId != null && newAccount == null) return false

        val newCategory = categoryId?.let { categoryRepository.findById(Id(it)) }
        if (categoryId != null && newCategory == null) return false

        val actualType = type ?: existing.type
        val actualCategoryId = categoryId ?: existing.categoryId
        val actualCategory = categoryRepository.findById(Id(actualCategoryId)) ?: return false

        if (!isTypeMatching(actualType, actualCategory.type)) return false

        // Откатываем старое изменение баланса
        oldAccount?.let { updateAccountBalance(it, -oldAmount, oldType) }

        // Применяем изменения
        val targetAccount = newAccount ?: oldAccount
        val actualAmount = amount ?: existing.amount

        // Обновляем поля
        type?.let { existing.type = it }
        bankAccountId?.let { existing.bankAccountId = it }
        amount?.let { existing.amount = it }
        date?.let { existing.date = it }
        description?.let { existing.description = it }
        categoryId?.let { existing.categoryId = it }

        // Создаем временную операцию для валидации
        try {
            operationFactory.createOperation(
                type = existing.type,
                bankAccountId = existing.bankAccountId,
                amount = existing.amount,
                date = existing.date,
                description = existing.description,
                categoryId = existing.categoryId
            )
        } catch (e: IllegalArgumentException) {
            // Восстанавливаем баланс в случае ошибки валидации
            targetAccount?.let { updateAccountBalance(it, actualAmount, actualType) }
            oldAccount?.let { updateAccountBalance(it, oldAmount, oldType) }
            return false
        }

        targetAccount?.let { updateAccountBalance(it, actualAmount, actualType) }
        return operationRepository.update(existing)
    }

    override fun deleteOperationById(id: Id): Boolean {
        val operation = operationRepository.findById(id) ?: return false
        val account = bankAccountRepository.findById(Id(operation.bankAccountId))
        account?.let { updateAccountBalance(it, -operation.amount, operation.type) }
        return operationRepository.delete(id)
    }

    override fun addExistingOperation(operation: Operation): Boolean {
        // Валидируем через фабрику
        try {
            operationFactory.createOperation(
                type = operation.type,
                bankAccountId = operation.bankAccountId,
                amount = operation.amount,
                date = operation.date,
                description = operation.description,
                categoryId = operation.categoryId
            )
        } catch (e: IllegalArgumentException) {
            return false
        }

        if (bankAccountRepository.findById(Id(operation.bankAccountId)) == null) return false
        if (categoryRepository.findById(Id(operation.categoryId)) == null) return false
        return operationRepository.save(operation)
    }

    override fun getAllOperations(): List<Operation> = operationRepository.findAll()

    override fun acceptVisitor(visitor: Visitor) = visitor.processOperations(operationRepository.findAll())

    private fun updateAccountBalance(account: BankAccount, amount: Double, type: OperationType) {
        account.balance += if (type == OperationType.INCOME) amount else -amount
    }

    private fun isTypeMatching(operationType: OperationType, categoryType: CategoryType): Boolean {
        return (operationType == OperationType.INCOME && categoryType == CategoryType.INCOME) ||
                (operationType == OperationType.EXPENSE && categoryType == CategoryType.EXPENSE)
    }
}