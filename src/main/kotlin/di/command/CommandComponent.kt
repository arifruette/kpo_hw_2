package di.command

import dagger.Subcomponent
import di.qualifiers.CreateOperation
import di.qualifiers.DeleteOperation
import di.qualifiers.ShowOperations
import di.qualifiers.UpdateOperation
import domain.command.Command

@Subcomponent
interface CommandComponent {
    @get:ShowOperations
    val showOperationsCommand: Command
    @get:CreateOperation
    val createOperationCommand: Command
    @get:DeleteOperation
    val deleteOperationCommand: Command
    @get:UpdateOperation
    val updateOperationCommand: Command

    @Subcomponent.Factory
    interface Factory {
        fun create(): CommandComponent
    }
}