package data.command.category

import domain.command.Command
import domain.facade.CategoryFacade
import domain.interaction.UserInteractionAgent
import domain.models.CategoryType

// Команда удаления категории
class DeleteCategoryCommand(
    private val categoryFacade: CategoryFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override val name: String = "delete_category"
    
    override fun execute() {
        val categories = categoryFacade.getAllCategories()
        
        if (categories.isEmpty()) {
            userInteractionAgent.showMessage("Нет категорий для удаления")
            return
        }
        
        userInteractionAgent.showMessage("Выберите категорию для удаления:")
        categories.forEachIndexed { index, category ->
            val type = if (category.type == CategoryType.INCOME) "Доход" else "Расход"
            userInteractionAgent.showMessage("${index + 1}. $type - ${category.name}")
        }
        
        val index = userInteractionAgent.inputInt("", 1) - 1
        if (index !in categories.indices) {
            userInteractionAgent.showMessage("Некорректный выбор")
            return
        }
        
        val category = categories[index]
        val confirm = userInteractionAgent.inputBoolean("Вы уверены, что хотите удалить категорию '${category.name}'?")
        
        if (confirm) {
            val success = categoryFacade.deleteCategoryById(category.id)
            if (success) {
                userInteractionAgent.showMessage("Категория успешно удалена")
            } else {
                userInteractionAgent.showMessage("Ошибка при удалении категории")
            }
        } else {
            userInteractionAgent.showMessage("Удаление отменено")
        }
    }
}