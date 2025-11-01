package presentation.pages

import domain.interaction.UserInteractionAgent
import presentation.Page
import presentation.contract.FinishRenderResult

// Главная страница приложения
class MainPage(
    override val userInteractionAgent: UserInteractionAgent
) : Page() {

    override fun render() {
        userInteractionAgent.showMessage("Меню учета финансов")
        userInteractionAgent.showMessage("1. Управление счетами")
        userInteractionAgent.showMessage("2. Управление категориями")
        userInteractionAgent.showMessage("3. Управление операциями")
        userInteractionAgent.showMessage("4. Аналитика")
        userInteractionAgent.showMessage("5. Выход")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите пункт меню:", 0)) {
            1 -> FinishRenderResult.Push(OperationListPage::class)
            2 -> FinishRenderResult.Push(OperationListPage::class)
            3 -> FinishRenderResult.Push(OperationListPage::class)
            4 -> FinishRenderResult.Push(OperationListPage::class)
            5 -> FinishRenderResult.Finish
            else -> FinishRenderResult.Pop
        }
    }
}