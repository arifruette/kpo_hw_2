package presentation.pages

import di.qualifiers.file.*
import domain.command.Command
import domain.interaction.UserInteractionAgent
import presentation.Page
import presentation.contract.FinishRenderResult
import javax.inject.Inject

class ExportDataPage @Inject constructor(
    override val userInteractionAgent: UserInteractionAgent,
    @ExportBankAccountsToCsv private val exportBankAccountsToCsv: Command,
    @ExportBankAccountsToJson private val exportBankAccountsToJson: Command,
    @ExportCategoriesToCsv private val exportCategoriesToCsv: Command,
    @ExportCategoriesToJson private val exportCategoriesToJson: Command,
    @ExportOperationsToCsv private val exportOperationsToCsv: Command,
    @ExportOperationsToJson private val exportOperationsToJson: Command,
) : Page() {
    
    override fun render() {
        userInteractionAgent.showMessage("=== Экспорт данных ===")
        userInteractionAgent.showMessage("Выберите тип данных для экспорта:")
        userInteractionAgent.showMessage("1. Банковские счета")
        userInteractionAgent.showMessage("2. Категории") 
        userInteractionAgent.showMessage("3. Операции")
        userInteractionAgent.showMessage("4. Назад")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите тип данных:", 0)) {
            1 -> {
                // Подстраница для выбора формата экспорта счетов
                when (userInteractionAgent.inputInt("Выберите формат:\n1. CSV\n2. JSON\n3. Отмена", 0)) {
                    1 -> { exportBankAccountsToCsv.execute(); FinishRenderResult.Pop }
                    2 -> { exportBankAccountsToJson.execute(); FinishRenderResult.Pop }
                    else -> FinishRenderResult.Pop
                }
            }
            2 -> {
                // Подстраница для выбора формата экспорта категорий
                when (userInteractionAgent.inputInt("Выберите формат:\n1. CSV\n2. JSON\n3. Отмена", 0)) {
                    1 -> { exportCategoriesToCsv.execute(); FinishRenderResult.Pop }
                    2 -> { exportCategoriesToJson.execute(); FinishRenderResult.Pop }
                    else -> FinishRenderResult.Pop
                }
            }
            3 -> {
                // Подстраница для выбора формата экспорта операций
                when (userInteractionAgent.inputInt("Выберите формат:\n1. CSV\n2. JSON\n3. Отмена", 0)) {
                    1 -> { exportOperationsToCsv.execute(); FinishRenderResult.Pop }
                    2 -> { exportOperationsToJson.execute(); FinishRenderResult.Pop }
                    else -> FinishRenderResult.Pop
                }
            }
            4 -> FinishRenderResult.Pop
            else -> FinishRenderResult.Pop
        }
    }
}