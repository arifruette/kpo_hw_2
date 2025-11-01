package di.pages

import dagger.Module
import dagger.Provides
import domain.annotation.command.CreateOperations
import domain.annotation.command.DeleteOperations
import domain.annotation.command.ShowOperations
import domain.command.Command
import domain.interaction.UserInteractionAgent
import presentation.DefaultPageRenderer
import presentation.Page
import presentation.pages.MainPage
import presentation.pages.OperationListPage
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
        @CreateOperations createOperationCommand: Command,
        @DeleteOperations deleteOperationCommand: Command
    ): OperationListPage {
        return OperationListPage(
            userInteractionAgent,
            showOperationsCommand,
            createOperationCommand,
            deleteOperationCommand
        )
    }

    @Provides
    @Singleton
    fun provideScreensMap(
        mainPage: MainPage,
        operationListPage: OperationListPage,
    ): Map<KClass<out Page>, @JvmSuppressWildcards Page> = mapOf(
        mainPage::class to mainPage,
        operationListPage::class to operationListPage
    )

    @Provides
    @Singleton
    fun provideDefaultPageRenderer(
        mainPage: MainPage,
        screensMap: Map<KClass<out Page>, @JvmSuppressWildcards Page>
    ) = DefaultPageRenderer(screensMap, mainPage)
}