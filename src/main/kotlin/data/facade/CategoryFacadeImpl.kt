package data.facade

import domain.facade.CategoryFacade
import domain.factory.CategoryFactory
import domain.models.Category
import domain.models.CategoryType
import domain.models.common.Id
import domain.repository.CategoryRepository
import domain.repository.OperationRepository
import javax.inject.Inject

class CategoryFacadeImpl @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val operationRepository: OperationRepository,
    private val categoryFactory: CategoryFactory
) : CategoryFacade {

    override fun addCategory(type: CategoryType, name: String): Boolean {
        try {
            if (categoryRepository.findByNameAndType(name, type) != null) return false
            val category = categoryFactory.createCategory(type, name)
            return categoryRepository.save(category)
        } catch (e: IllegalArgumentException) {
            return false
        }
    }

    override fun getCategoryById(id: Id): Category? {
        return categoryRepository.findById(id)
    }

    override fun updateCategoryById(id: Id, type: CategoryType?, name: String?): Boolean {
        val existing = categoryRepository.findById(id) ?: return false

        name?.let { newName ->
            val actualType = type ?: existing.type
            if (categoryRepository.findByNameAndType(newName, actualType) != null) {
                return false
            }
            existing.name = newName
        }

        type?.let { existing.type = it }
        return categoryRepository.update(existing)
    }

    override fun deleteCategoryById(id: Id): Boolean {
        val category = categoryRepository.findById(id) ?: return false
        operationRepository.deleteByCategoryId(category.id.raw)
        return categoryRepository.delete(id)
    }

    override fun addExistingCategory(category: Category): Boolean {
        if (categoryRepository.findByNameAndType(category.name, category.type) != null) return false
        return categoryRepository.save(category)
    }
}