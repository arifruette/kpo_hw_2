package data.command.file

import domain.command.Command
import domain.interaction.UserInteractionAgent
import domain.visitor.BaseElement
import domain.visitor.Visitor
import java.io.File
import javax.inject.Inject

class BaseExportCommand @Inject constructor(
    private val userInteractionAgent: UserInteractionAgent,
    private val visitor: Visitor,
    private val baseElement: BaseElement,
    private val dataTypeName: String,
    private val fileExtension: String
) : Command {

    override fun execute() {
        val fileName = userInteractionAgent.inputString("Введите название файла для экспорта $dataTypeName:")
        val fullFileName = "$fileName.$fileExtension"
        try {
            val content = baseElement.acceptVisitor(visitor)
            File(fullFileName).writeText(content)
            userInteractionAgent.showMessage("$dataTypeName успешно экспортированы в файл: $fileName")
        } catch (e: Exception) {
            userInteractionAgent.showMessage("Ошибка при экспорте: ${e.message}")
        }
    }

    override val name: String = "export_${dataTypeName.lowercase()}_to_file"
}