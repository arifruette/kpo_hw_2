package di.command

import dagger.Module
import dagger.Provides
import data.command.operation.CreateOperationCommand
import data.command.operation.DeleteOperationCommand
import data.command.operation.ShowOperationsCommand
import domain.annotation.command.CreateOperations
import domain.annotation.command.DeleteOperations
import domain.annotation.command.ShowOperations
import domain.command.Command
import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.interaction.UserInteractionAgent

@Module
object CommandModule {
    @Provides
    @ShowOperations
    fun provideShowOperationsCommand(
        operationFacade: OperationFacade,
        userInteractionAgent: UserInteractionAgent
    ): Command {
        return ShowOperationsCommand(operationFacade, userInteractionAgent)
    }

    @Provides
    @CreateOperations
    fun provideCreateOperationCommand(
        operationFacade: OperationFacade,
        bankAccountFacade: BankAccountFacade,
        categoryFacade: CategoryFacade,
        userInteractionAgent: UserInteractionAgent
    ): Command {
        return CreateOperationCommand(operationFacade, bankAccountFacade, categoryFacade, userInteractionAgent)
    }

    @Provides
    @DeleteOperations
    fun provideDeleteOperationCommand(
        operationFacade: OperationFacade,
        userInteractionAgent: UserInteractionAgent
    ): Command {
        return DeleteOperationCommand(operationFacade, userInteractionAgent)
    }
}