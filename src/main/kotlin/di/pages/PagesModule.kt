package di.pages

import dagger.Module
import dagger.Provides
import domain.annotation.command.CreateOperations
import domain.annotation.command.DeleteOperations
import domain.annotation.command.ShowOperations
import domain.command.Command
import domain.interaction.UserInteractionAgent
import presentation.pages.MainPage
import presentation.pages.OperationListPage
import javax.inject.Singleton

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
}