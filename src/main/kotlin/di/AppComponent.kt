package di

import dagger.Component
import di.command.file.ExportCommandsModule
import di.command.models.BankAccountsCommandModule
import di.command.models.CategoriesCommandModule
import di.command.models.OperationsCommandModule
import di.facade.FacadeModule
import di.factory.FactoryModule
import di.interaction.UserInteractionAgentModule
import di.pages.PagesComponent
import di.pages.PagesModule
import di.repository.RepositoryModule
import di.statistic.StatisticsModule
import di.visitor.VisitorModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        VisitorModule::class,
        RepositoryModule::class,
        FactoryModule::class,
        FacadeModule::class,
        OperationsCommandModule::class,
        CategoriesCommandModule::class,
        BankAccountsCommandModule::class,
        PagesModule::class,
        ExportCommandsModule::class,
        UserInteractionAgentModule::class,
        StatisticsModule::class
    ]
)
interface AppComponent {
    fun pagesComponent(): PagesComponent.Factory
}