package di.command.file

import dagger.Module
import dagger.Provides
import data.command.TimeComputingCommandDecorator
import data.command.file.BaseExportCommand
import di.qualifiers.file.*
import di.qualifiers.visitor.CsvVisitor
import di.qualifiers.visitor.JsonVisitor
import domain.command.Command
import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.interaction.UserInteractionAgent
import domain.statistic.StatisticReporter
import domain.visitor.Visitor
import javax.inject.Singleton

// ExportCommandsModule.kt
@Module
object ExportCommandsModule {

    @Provides
    @Singleton
    @ExportBankAccountsToCsv
    fun provideExportBankAccountsToCsvCommand(
        userInteractionAgent: UserInteractionAgent,
        @CsvVisitor csvVisitor: Visitor,
        bankAccountFacade: BankAccountFacade,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        baseCommand = BaseExportCommand(
            userInteractionAgent = userInteractionAgent,
            visitor = csvVisitor,
            baseElement = bankAccountFacade,
            dataTypeName = "Банковские счета",
            fileExtension = "csv"
        ),
        statisticReporter = statisticReporter
    )

    @Provides
    @Singleton
    @ExportBankAccountsToJson
    fun provideExportBankAccountsToJsonCommand(
        userInteractionAgent: UserInteractionAgent,
        @JsonVisitor jsonVisitor: Visitor,
        bankAccountFacade: BankAccountFacade,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        baseCommand = BaseExportCommand(
            userInteractionAgent = userInteractionAgent,
            visitor = jsonVisitor,
            baseElement = bankAccountFacade,
            dataTypeName = "Банковские счета",
            fileExtension = "json"
        ),
        statisticReporter = statisticReporter
    )

    @Provides
    @Singleton
    @ExportCategoriesToCsv
    fun provideExportCategoriesToCsvCommand(
        userInteractionAgent: UserInteractionAgent,
        @CsvVisitor csvVisitor: Visitor,
        categoryFacade: CategoryFacade,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        baseCommand = BaseExportCommand(
            userInteractionAgent = userInteractionAgent,
            visitor = csvVisitor,
            baseElement = categoryFacade,
            dataTypeName = "Категории",
            fileExtension = "csv"
        ),
        statisticReporter = statisticReporter
    )

    @Provides
    @Singleton
    @ExportCategoriesToJson
    fun provideExportCategoriesToJsonCommand(
        userInteractionAgent: UserInteractionAgent,
        @JsonVisitor jsonVisitor: Visitor,
        categoryFacade: CategoryFacade,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        baseCommand = BaseExportCommand(
            userInteractionAgent = userInteractionAgent,
            visitor = jsonVisitor,
            baseElement = categoryFacade,
            dataTypeName = "Категории",
            fileExtension = "json"
        ),
        statisticReporter = statisticReporter
    )

    @Provides
    @Singleton
    @ExportOperationsToCsv
    fun provideExportOperationsToCsvCommand(
        userInteractionAgent: UserInteractionAgent,
        @CsvVisitor csvVisitor: Visitor,
        operationFacade: OperationFacade,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        baseCommand = BaseExportCommand(
            userInteractionAgent = userInteractionAgent,
            visitor = csvVisitor,
            baseElement = operationFacade,
            dataTypeName = "Операции",
            fileExtension = "csv"
        ),
        statisticReporter = statisticReporter
    )

    @Provides
    @Singleton
    @ExportOperationsToJson
    fun provideExportOperationsToJsonCommand(
        userInteractionAgent: UserInteractionAgent,
        @JsonVisitor jsonVisitor: Visitor,
        operationFacade: OperationFacade,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        baseCommand = BaseExportCommand(
            userInteractionAgent = userInteractionAgent,
            visitor = jsonVisitor,
            baseElement = operationFacade,
            dataTypeName = "Операции",
            fileExtension = "json"
        ),
        statisticReporter = statisticReporter
    )
}