package data.command.operation

import domain.command.Command
import domain.facade.OperationFacade
import domain.interaction.UserInteractionAgent
import domain.models.OperationType
import java.time.format.DateTimeFormatter
import javax.inject.Inject

// Команда показа списка операций
class ShowOperationsCommand @Inject constructor(
    private val operationFacade: OperationFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {

    override fun execute() {
        val operations = operationFacade.getAllOperations()

        if (operations.isEmpty()) {
            userInteractionAgent.showMessage("Операций нет")
        } else {
            operations.forEachIndexed { index, operation ->
                val formattedDate = operation.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                val type = if (operation.type == OperationType.INCOME) "Доход" else "Расход"
                userInteractionAgent.showMessage("${index + 1}. $type - ${operation.amount} - $formattedDate")
            }
        }
    }

    override val name: String = "show_operations"
}