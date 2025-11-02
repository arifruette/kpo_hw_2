package presentation.pages

import di.qualifiers.models.CreateExpenseCategory
import di.qualifiers.models.CreateIncomeCategory
import di.qualifiers.models.DeleteCategory
import di.qualifiers.models.EditCategory
import di.qualifiers.models.ShowCategories
import domain.command.Command
import domain.interaction.UserInteractionAgent
import presentation.Page
import presentation.contract.FinishRenderResult

class CategoryListPage(
    override val userInteractionAgent: UserInteractionAgent,
    @ShowCategories private val showCategoriesCommand: Command,
    @CreateIncomeCategory private val createIncomeCategoryCommand: Command,
    @CreateExpenseCategory private val createExpenseCategoryCommand: Command,
    @EditCategory private val editCategoryCommand: Command,
    @DeleteCategory private val deleteCategoryCommand: Command
) : Page() {
    
    override fun render() {
        userInteractionAgent.showMessage("=== Управление категориями ===")
        showCategoriesCommand.execute()
        userInteractionAgent.showMessage("\nДействия:")
        userInteractionAgent.showMessage("1. Создать категорию дохода")
        userInteractionAgent.showMessage("2. Создать категорию расхода")
        userInteractionAgent.showMessage("3. Редактировать категорию")
        userInteractionAgent.showMessage("4. Удалить категорию")
        userInteractionAgent.showMessage("5. Назад")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите действие:", 0)) {
            1 -> {
                createIncomeCategoryCommand.execute()
                FinishRenderResult.Pop
            }
            2 -> {
                createExpenseCategoryCommand.execute()
                FinishRenderResult.Pop
            }
            3 -> {
                editCategoryCommand.execute()
                FinishRenderResult.Pop
            }
            4 -> {
                deleteCategoryCommand.execute()
                FinishRenderResult.Pop
            }
            5 -> FinishRenderResult.Pop
            else -> FinishRenderResult.Pop
        }
    }
}