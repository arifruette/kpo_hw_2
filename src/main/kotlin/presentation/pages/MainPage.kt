package presentation.pages

import domain.interaction.UserInteractionAgent
import presentation.Page
import presentation.contract.FinishRenderResult

class MainPage(
    override val userInteractionAgent: UserInteractionAgent
) : Page() {

    override fun render() {
        userInteractionAgent.showMessage("=== Меню учета финансов ===")
        userInteractionAgent.showMessage("1. Управление счетами")
        userInteractionAgent.showMessage("2. Управление категориями")
        userInteractionAgent.showMessage("3. Управление операциями")
        userInteractionAgent.showMessage("4. Статистика выполнения")
        userInteractionAgent.showMessage("5. Экспорт данных")
        userInteractionAgent.showMessage("6. Импорт данных")
        userInteractionAgent.showMessage("7. Выход")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите пункт меню:", 0)) {
            1 -> FinishRenderResult.Push(BankAccountListPage::class)
            2 -> FinishRenderResult.Push(CategoryListPage::class)
            3 -> FinishRenderResult.Push(OperationListPage::class)
            4 -> FinishRenderResult.Push(StatisticsPage::class)
            5 -> FinishRenderResult.Push(ExportDataPage::class)
            6 -> FinishRenderResult.Push(ImportDataPage::class)
            7 -> FinishRenderResult.Finish
            else -> FinishRenderResult.Finish
        }
    }
}