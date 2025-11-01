package di.pages

import presentation.pages.MainPage
import dagger.Module
import dagger.Provides
import di.qualifiers.*
import domain.command.Command
import domain.interaction.UserInteractionAgent
import domain.statistic.StatisticReporter
import presentation.DefaultPageRenderer
import presentation.Page
import presentation.pages.BankAccountListPage
import presentation.pages.OperationListPage
import presentation.pages.StatisticsPage
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
object PagesModule {
    @Provides
    @Singleton
    fun provideMainPage(userInteractionAgent: UserInteractionAgent): MainPage {
        return MainPage(userInteractionAgent)
    }


    @Provides
    @Singleton
    fun provideOperationListPage(
        userInteractionAgent: UserInteractionAgent,
        @ShowOperations showOperationsCommand: Command,
        @CreateOperation createOperationCommand: Command,
        @DeleteOperation deleteOperationCommand: Command,
        @UpdateOperation updateOperationCommand: Command,
    ): OperationListPage {
        return OperationListPage(
            userInteractionAgent,
            showOperationsCommand,
            createOperationCommand,
            deleteOperationCommand,
            updateOperationCommand
        )
    }

    @Provides
    @Singleton
    fun provideBankAccountListPage(
        userInteractionAgent: UserInteractionAgent,
        @ShowBankAccounts showBankAccountsCommand: Command,
        @CreateBankAccount createBankAccountCommand: Command,
        @UpdateBankAccount updateBankAccountCommand: Command,
        @DeleteBankAccount deleteBankAccountCommand: Command
    ): BankAccountListPage {
        return BankAccountListPage(
            userInteractionAgent,
            showBankAccountsCommand,
            createBankAccountCommand,
            updateBankAccountCommand,
            deleteBankAccountCommand
        )
    }

    @Provides
    @Singleton
    fun provideStatisticsPage(
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): StatisticsPage {
        return StatisticsPage(userInteractionAgent, statisticReporter)
    }

    @Provides
    @Singleton
    fun provideScreensMap(
        mainPage: MainPage,
        operationListPage: OperationListPage,
        statisticsPage: StatisticsPage,
        bankAccountListPage: BankAccountListPage
    ): Map<KClass<out Page>, @JvmSuppressWildcards Page> = mapOf(
        mainPage::class to mainPage,
        bankAccountListPage::class to bankAccountListPage,
        statisticsPage::class to statisticsPage,
        operationListPage::class to operationListPage
    )

    @Provides
    @Singleton
    fun provideDefaultPageRenderer(
        mainPage: MainPage,
        screensMap: Map<KClass<out Page>, @JvmSuppressWildcards Page>
    ) = DefaultPageRenderer(screensMap, mainPage)
}