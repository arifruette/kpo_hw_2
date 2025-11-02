package di.command.models

import dagger.Module
import dagger.Provides
import data.command.TimeComputingCommandDecorator
import data.command.account.CreateBankAccountCommand
import data.command.account.DeleteBankAccountCommand
import data.command.account.ShowBankAccountsCommand
import data.command.account.UpdateBankAccountCommand
import di.qualifiers.models.CreateBankAccount
import di.qualifiers.models.DeleteBankAccount
import di.qualifiers.models.ShowBankAccounts
import di.qualifiers.models.UpdateBankAccount
import domain.command.Command
import domain.facade.BankAccountFacade
import domain.interaction.UserInteractionAgent
import domain.statistic.StatisticReporter

@Module
object BankAccountsCommandModule {
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