package data.command.category

import domain.command.Command
import domain.facade.CategoryFacade
import domain.interaction.UserInteractionAgent
import domain.models.CategoryType

// Команда показа категорий
class ShowCategoriesCommand(
    private val categoryFacade: CategoryFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override val name: String = "show_categories"
    
    override fun execute() {
        val categories = categoryFacade.getAllCategories()
        
        val incomeCategories = categories.filter { it.type == CategoryType.INCOME }
        val expenseCategories = categories.filter { it.type == CategoryType.EXPENSE }
        
        userInteractionAgent.showMessage("=== Категории доходов ===")
        if (incomeCategories.isEmpty()) {
            userInteractionAgent.showMessage("Нет категорий")
        } else {
            incomeCategories.forEachIndexed { index, category ->
                userInteractionAgent.showMessage("${index + 1}. ${category.name}")
            }
        }
        
        userInteractionAgent.showMessage("\n=== Категории расходов ===")
        if (expenseCategories.isEmpty()) {
            userInteractionAgent.showMessage("Нет категорий")
        } else {
            expenseCategories.forEachIndexed { index, category ->
                userInteractionAgent.showMessage("${index + 1}. ${category.name}")
            }
        }
    }
}
