package presentation.pages

import di.qualifiers.file.ImportFromCsv
import di.qualifiers.file.ImportFromJson
import di.qualifiers.file.ImportFromXml
import domain.command.Command
import domain.interaction.UserInteractionAgent
import presentation.Page
import presentation.contract.FinishRenderResult
import javax.inject.Inject

class ImportDataPage @Inject constructor(
    override val userInteractionAgent: UserInteractionAgent,
    @ImportFromCsv private val importFromCsvCommand: Command,
    @ImportFromJson private val importFromJsonCommand: Command,
    @ImportFromXml private val importFromXmlCommand: Command
) : Page() {

    override fun render() {
        userInteractionAgent.showMessage("=== Импорт данных ===")
        userInteractionAgent.showMessage("1. Импорт из CSV")
        userInteractionAgent.showMessage("2. Импорт из JSON")
        userInteractionAgent.showMessage("3. Импорт из XML")
        userInteractionAgent.showMessage("4. Назад")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите формат:", 0)) {
            1 -> { importFromCsvCommand.execute(); FinishRenderResult.Pop }
            2 -> { importFromJsonCommand.execute(); FinishRenderResult.Pop }
            3 -> { importFromXmlCommand.execute(); FinishRenderResult.Pop }
            4 -> FinishRenderResult.Pop
            else -> FinishRenderResult.Pop
        }
    }
}