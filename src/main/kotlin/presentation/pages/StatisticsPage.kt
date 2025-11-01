package presentation.pages

import domain.interaction.UserInteractionAgent
import domain.statistic.StatisticReporter
import presentation.Page
import presentation.contract.FinishRenderResult

class StatisticsPage(
    override val userInteractionAgent: UserInteractionAgent,
    private val statisticReporter: StatisticReporter
) : Page() {
    
    override fun render() {
        userInteractionAgent.showMessage("=== Статистика выполнения команд ===")
        
        val report = statisticReporter.getReport()
        if (report.isEmpty()) {
            userInteractionAgent.showMessage("Статистика пока недоступна")
        } else {
            report.forEach { line ->
                userInteractionAgent.showMessage(line)
            }
        }
        
        userInteractionAgent.showMessage("\nДействия:")
        userInteractionAgent.showMessage("1. Очистить статистику")
        userInteractionAgent.showMessage("2. Назад")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите действие:", 0)) {
            1 -> {
                statisticReporter.clear()
                userInteractionAgent.showMessage("Статистика очищена")
                FinishRenderResult.Pop
            }
            2 -> FinishRenderResult.Pop
            else -> FinishRenderResult.Pop
        }
    }
}