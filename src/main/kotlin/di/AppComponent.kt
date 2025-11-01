package di

import dagger.Component
import di.command.CommandComponent
import di.command.CommandModule
import di.facade.FacadeComponent
import di.facade.FacadeModule
import di.factory.FactoryComponent
import di.factory.FactoryModule
import di.interaction.UserInteractionAgentComponent
import di.interaction.UserInteractionAgentModule
import di.pages.PagesComponent
import di.pages.PagesModule
import di.repository.RepositoryComponent
import di.repository.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoryModule::class,
    FactoryModule::class,
    FacadeModule::class,
    CommandModule::class,
    PagesModule::class,
    UserInteractionAgentModule::class
])
interface AppComponent {
    fun repositoryComponent(): RepositoryComponent.Factory
    fun factoryComponent(): FactoryComponent.Factory
    fun facadeComponent(): FacadeComponent.Factory
    fun commandComponent(): CommandComponent.Factory
    fun pagesComponent(): PagesComponent.Factory
    fun userInteractionAgentComponent(): UserInteractionAgentComponent.Factory
}