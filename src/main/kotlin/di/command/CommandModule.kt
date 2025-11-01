package di.command

import dagger.Module
import dagger.Provides
import data.command.TimeComputingCommandDecorator
import data.command.account.CreateBankAccountCommand
import data.command.account.DeleteBankAccountCommand
import data.command.account.ShowBankAccountsCommand
import data.command.account.UpdateBankAccountCommand
import data.command.operation.CreateOperationCommand
import data.command.operation.DeleteOperationCommand
import data.command.operation.EditOperationCommand
import data.command.operation.ShowOperationsCommand
import di.qualifiers.*
import domain.command.Command
import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.interaction.UserInteractionAgent
import domain.statistic.StatisticReporter

@Module
object CommandModule {

    // region operations command
    @Provides
    @ShowOperations
    fun provideShowOperationsCommand(
        operationFacade: OperationFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter,
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = ShowOperationsCommand(operationFacade, userInteractionAgent),
            statisticReporter = statisticReporter
        )
    }

    @Provides
    @CreateOperation
    fun provideCreateOperationCommand(
        operationFacade: OperationFacade,
        bankAccountFacade: BankAccountFacade,
        categoryFacade: CategoryFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = CreateOperationCommand(
                operationFacade = operationFacade,
                bankAccountFacade = bankAccountFacade,
                categoryFacade = categoryFacade,
                userInteractionAgent = userInteractionAgent
            ), statisticReporter = statisticReporter
        )
    }

    @Provides
    @DeleteOperation
    fun provideDeleteOperationCommand(
        operationFacade: OperationFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = DeleteOperationCommand(
                operationFacade,
                userInteractionAgent
            ), statisticReporter = statisticReporter
        )
    }

    @Provides
    @UpdateOperation
    fun provideEditOperationCommand(
        operationFacade: OperationFacade,
        bankAccountFacade: BankAccountFacade,
        categoryFacade: CategoryFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = EditOperationCommand(
                operationFacade,
                bankAccountFacade,
                categoryFacade,
                userInteractionAgent
            ),
            statisticReporter = statisticReporter
        )
    }
    // endregion

    // region account commands
    @Provides
    @ShowBankAccounts
    fun provideShowBankAccountsCommand(
        bankAccountFacade: BankAccountFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter,
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = ShowBankAccountsCommand(bankAccountFacade, userInteractionAgent),
            statisticReporter = statisticReporter
        )
    }

    @Provides
    @CreateBankAccount
    fun provideCreateBankAccountCommand(
        bankAccountFacade: BankAccountFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = CreateBankAccountCommand(bankAccountFacade, userInteractionAgent),
            statisticReporter = statisticReporter
        )
    }

    @Provides
    @UpdateBankAccount
    fun provideUpdateBankAccountCommand(
        bankAccountFacade: BankAccountFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = UpdateBankAccountCommand(bankAccountFacade, userInteractionAgent),
            statisticReporter = statisticReporter
        )
    }

    @Provides
    @DeleteBankAccount
    fun provideDeleteBankAccountCommand(
        bankAccountFacade: BankAccountFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = DeleteBankAccountCommand(bankAccountFacade, userInteractionAgent),
            statisticReporter = statisticReporter
        )
    }
    // endregion
}