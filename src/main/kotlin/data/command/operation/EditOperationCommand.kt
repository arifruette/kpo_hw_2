package data.command.operation

import domain.command.Command
import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.interaction.UserInteractionAgent
import domain.models.CategoryType
import domain.models.OperationType
import java.time.LocalDateTime
import javax.inject.Inject

class EditOperationCommand @Inject constructor(
    private val operationFacade: OperationFacade,
    private val bankAccountFacade: BankAccountFacade,
    private val categoryFacade: CategoryFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override fun execute() {
        val operations = operationFacade.getAllOperations()
        
        if (operations.isEmpty()) {
            userInteractionAgent.showMessage("Нет операций для редактирования")
            return
        }
        
        userInteractionAgent.showMessage("Выберите операцию для редактирования:")
        operations.forEachIndexed { index, operation ->
            val type = if (operation.type == OperationType.INCOME) "Доход" else "Расход"
            userInteractionAgent.showMessage("${index + 1}. $type - ${operation.amount} - ${operation.date} - ${operation.description}")
        }
        
        val index = userInteractionAgent.inputInt("", 1) - 1
        if (index !in operations.indices) {
            userInteractionAgent.showMessage("Некорректный выбор")
            return
        }
        
        val operation = operations[index]
        userInteractionAgent.showMessage("Редактирование операции:")
        userInteractionAgent.showMessage("Текущие данные:")
        userInteractionAgent.showMessage("Тип: ${operation.type}")
        userInteractionAgent.showMessage("Сумма: ${operation.amount}")
        userInteractionAgent.showMessage("Дата: ${operation.date}")
        userInteractionAgent.showMessage("Описание: ${operation.description ?: "нет"}")
        
        // Выбор нового типа операции
        userInteractionAgent.showMessage("Новый тип операции (оставьте пустым чтобы не менять):")
        userInteractionAgent.showMessage("1. Доход")
        userInteractionAgent.showMessage("2. Расход")
        val typeInput = userInteractionAgent.inputString("", "")
        val newType = when (typeInput) {
            "1" -> OperationType.INCOME
            "2" -> OperationType.EXPENSE
            else -> null
        }

        // Выбор нового счета (если нужно)
        var newAccountId: String? = null
        if (newType != null || userInteractionAgent.inputBoolean("Хотите изменить счет?")) {
            val accounts = bankAccountFacade.getAllBankAccounts()
            if (accounts.isEmpty()) {
                userInteractionAgent.showMessage("Нет доступных счетов")
                return
            }
            
            userInteractionAgent.showMessage("Выберите новый счет:")
            accounts.forEachIndexed { i, account ->
                userInteractionAgent.showMessage("${i + 1}. ${account.name} - ${account.balance}")
            }
            val accountIndex = userInteractionAgent.inputInt("", 1) - 1
            if (accountIndex in accounts.indices) {
                newAccountId = accounts[accountIndex].id.raw
            }
        }

        // Выбор новой категории (если нужно)
        var newCategoryId: String? = null
        val actualType = newType ?: operation.type
        if (newType != null || userInteractionAgent.inputBoolean("Хотите изменить категорию?")) {
            val categories = categoryFacade.getAllCategories()
                .filter { it.type == if (actualType == OperationType.INCOME) CategoryType.INCOME else CategoryType.EXPENSE }
            
            if (categories.isEmpty()) {
                userInteractionAgent.showMessage("Нет доступных категорий")
                return
            }
            
            userInteractionAgent.showMessage("Выберите новую категорию:")
            categories.forEachIndexed { i, category ->
                userInteractionAgent.showMessage("${i + 1}. ${category.name}")
            }
            val categoryIndex = userInteractionAgent.inputInt("", 1) - 1
            if (categoryIndex in categories.indices) {
                newCategoryId = categories[categoryIndex].id.raw
            }
        }

        // Ввод новой суммы
        val newAmount = userInteractionAgent.inputDouble("Новая сумма (оставьте пустым чтобы не менять):")
        
        // Ввод нового описания
        val newDescription = userInteractionAgent.inputString("Новое описание (оставьте пустым чтобы не менять):", "")

        // Ввод новой даты
        var newDate: LocalDateTime? = null
        if (userInteractionAgent.inputBoolean("Хотите изменить дату?")) {
            // Простая реализация - используем текущую дату
            // В реальном приложении можно добавить парсинг даты
            newDate = LocalDateTime.now()
            userInteractionAgent.showMessage("Дата изменена на текущую: $newDate")
        }

        val success = operationFacade.updateOperationById(
            id = operation.id,
            type = newType,
            bankAccountId = newAccountId,
            amount = newAmount,
            date = newDate,
            description = newDescription.ifBlank { null },
            categoryId = newCategoryId
        )

        if (success) {
            userInteractionAgent.showMessage("Операция успешно обновлена!")
        } else {
            userInteractionAgent.showMessage("Ошибка при обновлении операции")
        }
    }

    override val name: String = "edit_operation"
}