package presentation.pages

import di.qualifiers.CreateOperation
import di.qualifiers.DeleteOperation
import di.qualifiers.ShowOperations
import di.qualifiers.UpdateOperation
import domain.command.Command
import domain.interaction.UserInteractionAgent
import presentation.Page
import presentation.contract.FinishRenderResult
import javax.inject.Inject

class OperationListPage @Inject constructor(
    override val userInteractionAgent: UserInteractionAgent,
    @field:ShowOperations private val showOperationsCommand: Command,
    @field:CreateOperation private val createOperationCommand: Command,
    @field:DeleteOperation private val deleteOperationCommand: Command,
    @field:UpdateOperation private val updateOperationCommand: Command,
) : Page() {

    override fun render() {
        userInteractionAgent.showMessage("=== Управление операциями ===")
        // TODO: а надо ли
        showOperationsCommand.execute()
        userInteractionAgent.showMessage("\nДействия:")
        userInteractionAgent.showMessage("1. Создать операцию")
        userInteractionAgent.showMessage("2. Редактировать операцию")
        userInteractionAgent.showMessage("3. Удалить операцию")
        userInteractionAgent.showMessage("4. Назад")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите действие:", 0)) {
            1 -> {
                createOperationCommand.execute()
                FinishRenderResult.Pop
            }
            2 -> {
                updateOperationCommand.execute()
                FinishRenderResult.Pop
            }
            3 -> {
                deleteOperationCommand.execute()
                FinishRenderResult.Pop
            }
            4 -> FinishRenderResult.Pop
            else -> FinishRenderResult.Pop
        }
    }
}