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

// Команда создания операции
class CreateOperationCommand @Inject constructor(
    private val operationFacade: OperationFacade,
    private val bankAccountFacade: BankAccountFacade,
    private val categoryFacade: CategoryFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override fun execute() {
        userInteractionAgent.showMessage("Тип операции:")
        userInteractionAgent.showMessage("1. Доход")
        userInteractionAgent.showMessage("2. Расход")
        val type = when (userInteractionAgent.inputInt("Выберите тип:", 1)) {
            1 -> OperationType.INCOME
            2 -> OperationType.EXPENSE
            else -> throw IllegalStateException()
        }

        val accounts = bankAccountFacade.getAllBankAccounts()
        if (accounts.isEmpty()) {
            userInteractionAgent.showMessage("Нет доступных счетов. Сначала создайте счет.")
            return
        }
        
        userInteractionAgent.showMessage("Выберите счет:")
        accounts.forEachIndexed { index, account ->
            userInteractionAgent.showMessage("${index + 1}. ${account.name} - ${account.balance}")
        }
        val accountIndex = userInteractionAgent.inputInt("", 1) - 1
        val account = accounts[accountIndex]

        val categories = categoryFacade.getAllCategories()
            .filter { it.type == if (type == OperationType.INCOME) CategoryType.INCOME else CategoryType.EXPENSE }
        
        if (categories.isEmpty()) {
            userInteractionAgent.showMessage("Нет доступных категорий. Сначала создайте категорию.")
            return
        }
        
        userInteractionAgent.showMessage("Выберите категорию:")
        categories.forEachIndexed { index, category ->
            userInteractionAgent.showMessage("${index + 1}. ${category.name}")
        }
        val categoryIndex = userInteractionAgent.inputInt("", 1) - 1
        val category = categories[categoryIndex]

        val amount = userInteractionAgent.inputDouble("Введите сумму:")
        val description = userInteractionAgent.inputString("Введите описание (необязательно):", "")
        val date = LocalDateTime.now()

        val success = operationFacade.addOperation(
            type, account.id.raw, amount, date,
            description.ifBlank { null },
            category.id.raw
        )

        if (success) {
            userInteractionAgent.showMessage("Операция успешно создана!")
        } else {
            userInteractionAgent.showMessage("Ошибка при создании операции")
        }
    }

    override val name: String = "create_operation"
}