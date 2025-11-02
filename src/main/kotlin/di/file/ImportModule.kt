package di.file

import dagger.Module
import dagger.Provides
import data.adapter.XmlToJsonAdapter
import data.command.TimeComputingCommandDecorator
import data.command.file.BaseImportCommand
import data.file.CsvImporter
import data.file.JsonImporter
import data.file.XmlImporter
import di.qualifiers.file.ImportFromCsv
import di.qualifiers.file.ImportFromJson
import di.qualifiers.file.ImportFromXml
import domain.command.Command
import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.interaction.UserInteractionAgent
import domain.statistic.StatisticReporter
import javax.inject.Singleton

@Module
object ImportModule {

    @Provides
    @Singleton
    fun provideCsvImporter(
        bankAccountFacade: BankAccountFacade,
        categoryFacade: CategoryFacade,
        operationFacade: OperationFacade
    ): CsvImporter = CsvImporter(bankAccountFacade, categoryFacade, operationFacade)

    @Provides
    @Singleton
    fun provideJsonImporter(
        bankAccountFacade: BankAccountFacade,
        categoryFacade: CategoryFacade,
        operationFacade: OperationFacade
    ): JsonImporter = JsonImporter(bankAccountFacade, categoryFacade, operationFacade)

    @Provides
    @Singleton
    @ImportFromCsv
    fun provideImportFromCsvCommand(
        userInteractionAgent: UserInteractionAgent,
        csvImporter: CsvImporter,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        baseCommand = BaseImportCommand(
            userInteractionAgent, csvImporter, name = "csv_import_command",
            extension = "csv"
        ),
        statisticReporter
    )

    @Provides
    @Singleton
    @ImportFromJson
    fun provideImportFromJsonCommand(
        userInteractionAgent: UserInteractionAgent,
        jsonImporter: JsonImporter,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        baseCommand = BaseImportCommand(
            userInteractionAgent, jsonImporter, name = "json_import_command",
            extension = "json"
        ),
        statisticReporter
    )

    @Provides
    @Singleton
    @ImportFromXml
    fun provideImportFromXmlCommand(
        userInteractionAgent: UserInteractionAgent,
        xmlImporter: XmlImporter,
        statisticReporter: StatisticReporter
    ): Command = TimeComputingCommandDecorator(
        BaseImportCommand(
            userInteractionAgent, xmlImporter,
            extension = "xml",
            name = "xml_import_command",
        ),
        statisticReporter
    )

    @Provides
    @Singleton
    fun provideXmlToJsonAdapter() = XmlToJsonAdapter()

    @Provides
    @Singleton
    fun provideXmlImporter(
        bankAccountFacade: BankAccountFacade,
        categoryFacade: CategoryFacade,
        operationFacade: OperationFacade,
        xmlAdapter: XmlToJsonAdapter,
        jsonImporter: JsonImporter,
    ) = XmlImporter(
        bankAccountFacade = bankAccountFacade,
        categoryFacade = categoryFacade,
        operationFacade = operationFacade,
        xmlAdapter = xmlAdapter,
        jsonImporter = jsonImporter,
    )
}