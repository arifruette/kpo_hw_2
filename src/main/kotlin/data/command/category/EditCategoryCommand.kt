package data.command.category

import domain.command.Command
import domain.facade.CategoryFacade
import domain.interaction.UserInteractionAgent
import domain.models.CategoryType

// Команда редактирования категории
class EditCategoryCommand(
    private val categoryFacade: CategoryFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {

    override val name: String = "edit_category"

    override fun execute() {
        val categories = categoryFacade.getAllCategories()

        if (categories.isEmpty()) {
            userInteractionAgent.showMessage("Нет категорий для редактирования")
            return
        }

        userInteractionAgent.showMessage("Выберите категорию для редактирования:")
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
        userInteractionAgent.showMessage("Редактирование категории '${category.name}':")

        val newName = userInteractionAgent.inputString("Введите новое название (оставьте пустым чтобы не менять):", "")

        userInteractionAgent.showMessage("Новый тип категории (оставьте пустым чтобы не менять):")
        userInteractionAgent.showMessage("1. Доход")
        userInteractionAgent.showMessage("2. Расход")
        val typeInput = userInteractionAgent.inputString("", "")
        val newType = when (typeInput) {
            "1" -> CategoryType.INCOME
            "2" -> CategoryType.EXPENSE
            else -> null
        }

        val nameToSet = newName.ifBlank { null }

        // Проверяем уникальность (если имя изменилось)
        if (nameToSet != null && nameToSet != category.name) {
            val existingCategories = categoryFacade.getAllCategories()
            val exists = existingCategories.any {
                it.name == nameToSet && it.type == (newType ?: category.type) && it.id != category.id
            }
            if (exists) {
                userInteractionAgent.showMessage("Категория с таким именем и типом уже существует")
                return
            }
        }

        // Обновляем категорию
        val success = categoryFacade.updateCategoryById(
            id = category.id,
            type = newType,
            name = nameToSet
        )

        if (success) {
            userInteractionAgent.showMessage("Категория успешно обновлена!")
        } else {
            userInteractionAgent.showMessage("Ошибка при обновлении категории")
        }
    }
}