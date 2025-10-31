package domain.facade

import domain.models.Category
import domain.models.CategoryType
import domain.models.common.Id

interface CategoryFacade {

    fun addCategory(type: CategoryType, name: String): Boolean

    fun getCategoryById(id: Id): Category?

    fun updateCategoryById(id: Id, type: CategoryType?, name: String?): Boolean

    fun deleteCategoryById(id: Id): Boolean

    fun addExistingCategory(category: Category): Boolean

}