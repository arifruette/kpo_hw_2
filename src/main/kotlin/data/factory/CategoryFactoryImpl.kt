package data.factory

import domain.factory.CategoryFactory
import domain.models.Category
import domain.models.CategoryType
import domain.models.common.IdGenerator
import javax.inject.Inject

class CategoryFactoryImpl @Inject constructor(): CategoryFactory {
    override fun createCategory(type: CategoryType, name: String): Category {
        if (name.isBlank()) throw IllegalArgumentException("Category name cannot be blank")

        return Category(
            id = IdGenerator.generateId(),
            type = type,
            name = name
        )
    }
}