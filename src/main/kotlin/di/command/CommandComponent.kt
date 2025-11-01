package di.command

import dagger.Subcomponent
import domain.annotation.command.CreateOperations
import domain.annotation.command.DeleteOperations
import domain.annotation.command.ShowOperations
import domain.command.Command

@Subcomponent
interface CommandComponent {
    @get:ShowOperations
    val showOperationsCommand: Command
    @get:CreateOperations
    val createOperationCommand: Command
    @get:DeleteOperations
    val deleteOperationCommand: Command

    @Subcomponent.Factory
    interface Factory {
        fun create(): CommandComponent
    }
}