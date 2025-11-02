import domain.command.Command
import domain.facade.CategoryFacade
import domain.interaction.UserInteractionAgent
import domain.models.CategoryType

// Команда создания категории
class CreateCategoryCommand(
    private val categoryFacade: CategoryFacade,
    private val userInteractionAgent: UserInteractionAgent,
    private val categoryType: CategoryType
) : Command {

    override val name: String = "create_category_${categoryType.name.lowercase()}"

    override fun execute() {
        val name = userInteractionAgent.inputString("Введите название категории:")

        val success = categoryFacade.addCategory(categoryType, name)
        if (success) {
            val typeName = if (categoryType == CategoryType.INCOME) "дохода" else "расхода"
            userInteractionAgent.showMessage("Категория $typeName '$name' успешно создана!")
        } else {
            userInteractionAgent.showMessage("Ошибка при создании категории")
        }
    }
}