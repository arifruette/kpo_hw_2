package data.command.file

import domain.command.Command
import domain.file.FileImporter
import domain.file.ImportConfig
import domain.interaction.UserInteractionAgent

class BaseImportCommand(
    private val userInteractionAgent: UserInteractionAgent,
    private val importer: FileImporter,
    private val extension: String,
    override val name: String
) : Command {

    override fun execute() {
        userInteractionAgent.showMessage("=== Импорт данных ===")

        val categoriesPath = userInteractionAgent.inputString("Файл категорий: (пусто если не надо)", "")
            .takeIf { it.isNotBlank() }
            ?.let {
                "$it.$extension"
            }

        val bankAccountsPath = userInteractionAgent.inputString("Файл счетов: (пусто если не надо)", "")
            .takeIf { it.isNotBlank() }
            ?.let {
                "$it.$extension"
            }

        val operationsPath = userInteractionAgent.inputString("Файл операций: (пусто если не надо)", "")
            .takeIf { it.isNotBlank() }
            ?.let {
                "$it.$extension"
            }
        val config = ImportConfig.Builder()
            .categoriesFile(categoriesPath)
            .bankAccountsFile(bankAccountsPath)
            .operationsFile(operationsPath)
            .build()

        if (!config.importAnything) {
            userInteractionAgent.showMessage("Не выбрано ни одного файла")
            return
        }

        try {
            importer.importData(config)
            userInteractionAgent.showMessage("Импорт завершен")
        } catch (e: Exception) {
            userInteractionAgent.showMessage("Ошибка импорта: ${e.message}")
        }
    }
}