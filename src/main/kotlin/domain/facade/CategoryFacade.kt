package domain.facade

import domain.models.Category
import domain.models.CategoryType
import domain.models.common.Id
import domain.visitor.BaseElement

interface CategoryFacade : BaseElement {

    fun addCategory(type: CategoryType, name: String): Boolean

    fun getCategoryById(id: Id): Category?

    fun updateCategoryById(id: Id, type: CategoryType?, name: String?): Boolean

    fun deleteCategoryById(id: Id): Boolean

    fun addExistingCategory(category: Category): Boolean

    fun getAllCategories(): List<Category>

}