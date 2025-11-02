package di.pages

import dagger.Module
import dagger.Provides
import di.qualifiers.file.*
import di.qualifiers.models.*
import domain.command.Command
import domain.interaction.UserInteractionAgent
import domain.statistic.StatisticReporter
import presentation.DefaultPageRenderer
import presentation.Page
import presentation.pages.*
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
    fun provideCategoryListPage(
        userInteractionAgent: UserInteractionAgent,
        @ShowCategories showCategoriesCommand: Command,
        @CreateIncomeCategory createIncomeCategoryCommand: Command,
        @CreateExpenseCategory createExpenseCategoryCommand: Command,
        @EditCategory editCategoryCommand: Command,
        @DeleteCategory deleteCategoryCommand: Command
    ): CategoryListPage {
        return CategoryListPage(
            userInteractionAgent,
            showCategoriesCommand,
            createIncomeCategoryCommand,
            createExpenseCategoryCommand,
            editCategoryCommand,
            deleteCategoryCommand
        )
    }

    @Provides
    @Singleton
    fun provideExportDataPage(
        userInteractionAgent: UserInteractionAgent,
        @ExportBankAccountsToCsv exportBankAccountsToCsv: Command,
        @ExportBankAccountsToJson exportBankAccountsToJson: Command,
        @ExportCategoriesToCsv exportCategoriesToCsv: Command,
        @ExportCategoriesToJson exportCategoriesToJson: Command,
        @ExportOperationsToCsv exportOperationsToCsv: Command,
        @ExportOperationsToJson exportOperationsToJson: Command,
    ): ExportDataPage = ExportDataPage(
        userInteractionAgent = userInteractionAgent,
        exportBankAccountsToCsv = exportBankAccountsToCsv,
        exportBankAccountsToJson = exportBankAccountsToJson,
        exportCategoriesToCsv = exportCategoriesToCsv,
        exportCategoriesToJson = exportCategoriesToJson,
        exportOperationsToCsv = exportOperationsToCsv,
        exportOperationsToJson = exportOperationsToJson
    )

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
        categoryListPage: CategoryListPage,
        exportDataPage: ExportDataPage,
        statisticsPage: StatisticsPage,
        bankAccountListPage: BankAccountListPage
    ): Map<KClass<out Page>, @JvmSuppressWildcards Page> = mapOf(
        mainPage::class to mainPage,
        bankAccountListPage::class to bankAccountListPage,
        categoryListPage::class to categoryListPage,
        exportDataPage::class to exportDataPage,
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