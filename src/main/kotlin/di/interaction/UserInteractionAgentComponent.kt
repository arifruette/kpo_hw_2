package di.interaction

import dagger.Subcomponent
import domain.interaction.UserInteractionAgent

@Subcomponent
interface UserInteractionAgentComponent {
    val userInteractionAgent: UserInteractionAgent

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserInteractionAgentComponent
    }
}