package data.command.operation

import domain.command.Command
import domain.facade.OperationFacade
import domain.interaction.UserInteractionAgent
import domain.models.OperationType
import javax.inject.Inject

// Команда удаления операции
class DeleteOperationCommand @Inject constructor(
    private val operationFacade: OperationFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override fun execute() {
        val operations = operationFacade.getAllOperations()
        
        if (operations.isEmpty()) {
            userInteractionAgent.showMessage("Нет операций для удаления")
            return
        }
        
        userInteractionAgent.showMessage("Выберите операцию для удаления:")
        operations.forEachIndexed { index, operation ->
            val type = if (operation.type == OperationType.INCOME) "Доход" else "Расход"
            userInteractionAgent.showMessage("${index + 1}. $type - ${operation.amount} - ${operation.date}")
        }
        
        val index = userInteractionAgent.inputInt("", 1) - 1
        if (index in operations.indices) {
            val operation = operations[index]
            val confirm = userInteractionAgent.inputString("Вы уверены, что хотите удалить операцию? (y/n)", "")
                .equals("y", ignoreCase = true)
            
            if (confirm) {
                val success = operationFacade.deleteOperationById(operation.id)
                if (success) {
                    userInteractionAgent.showMessage("Операция успешно удалена")
                } else {
                    userInteractionAgent.showMessage("Ошибка при удалении операции")
                }
            }
        } else {
            userInteractionAgent.showMessage("Некорректный выбор")
        }
    }

    override val name: String = "delete_operation"
}