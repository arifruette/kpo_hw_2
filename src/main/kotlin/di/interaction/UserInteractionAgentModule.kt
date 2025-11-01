package di.interaction

import dagger.Binds
import dagger.Module
import data.interaction.ConsoleUserInteractionAgent
import domain.interaction.UserInteractionAgent
import javax.inject.Singleton

@Module
interface UserInteractionAgentModule {
    @Binds
    @Singleton
    fun bindUserInteractionAgent(impl: ConsoleUserInteractionAgent): UserInteractionAgent
}