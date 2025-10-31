
package domain.repository

import domain.models.Category
import domain.models.CategoryType

interface CategoryRepository : Repository<Category> {
    fun findByNameAndType(name: String, type: CategoryType): Category?
}