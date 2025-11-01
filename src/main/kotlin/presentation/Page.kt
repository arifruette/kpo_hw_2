
package presentation

import domain.interaction.UserInteractionAgent
import presentation.contract.FinishRenderResult

abstract class Page {
    abstract val userInteractionAgent: UserInteractionAgent
    abstract fun render()
    
    open fun finishRendering(): FinishRenderResult {
        userInteractionAgent.waitForNextStep()
        return FinishRenderResult.Pop
    }
}