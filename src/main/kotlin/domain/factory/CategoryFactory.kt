package domain.factory

import domain.models.Category
import domain.models.CategoryType

interface CategoryFactory {
    fun createCategory(type: CategoryType, name: String): Category
}