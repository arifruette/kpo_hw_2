package data.repository

import domain.models.Category
import domain.models.CategoryType
import domain.models.common.Id
import domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(): CategoryRepository {
    private val categories = mutableMapOf<Id, Category>()

    override fun findById(id: Id): Category? = categories[id]
    override fun findAll(): List<Category> = categories.values.toList()
    override fun save(entity: Category): Boolean {
        categories[entity.id] = entity
        return true
    }

    override fun update(entity: Category): Boolean {
        if (!categories.containsKey(entity.id)) return false
        categories[entity.id] = entity
        return true
    }

    override fun delete(id: Id): Boolean = categories.remove(id) != null
    override fun findByNameAndType(name: String, type: CategoryType): Category? =
        categories.values.find { it.name == name && it.type == type }
}