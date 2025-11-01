package presentation.pages

import domain.annotation.command.CreateOperations
import domain.annotation.command.DeleteOperations
import domain.annotation.command.ShowOperations
import domain.command.Command
import domain.interaction.UserInteractionAgent
import presentation.Page
import presentation.contract.FinishRenderResult

class OperationListPage(
    override val userInteractionAgent: UserInteractionAgent,
    @field:ShowOperations private val showOperationsCommand: Command,
    @field:CreateOperations private val createOperationCommand: Command,
    @field:DeleteOperations private val deleteOperationCommand: Command
) : Page() {

    override fun render() {
        userInteractionAgent.showMessage("Управление операциями\n")
        
        // Просто вызываем команду показа операций
        showOperationsCommand.execute()
        
        userInteractionAgent.showMessage("\nДействия:")
        userInteractionAgent.showMessage("1. Создать операцию")
        userInteractionAgent.showMessage("2. Удалить операцию")
        userInteractionAgent.showMessage("3. Назад")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите действие:", 0)) {
            1 -> {
                // Просто вызываем команду создания операции
                createOperationCommand.execute()
                FinishRenderResult.Pop
            }
            2 -> {
                // Просто вызываем команду удаления операции
                deleteOperationCommand.execute()
                FinishRenderResult.Pop
            }
            3 -> FinishRenderResult.Pop
            else -> FinishRenderResult.Pop
        }
    }
}