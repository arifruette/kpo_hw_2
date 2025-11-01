
package data.repository

import domain.models.Operation
import domain.models.common.Id
import domain.repository.OperationRepository
import javax.inject.Inject

class OperationRepositoryImpl @Inject constructor(): OperationRepository {
    private val operations = mutableMapOf<Id, Operation>()
    
    override fun findById(id: Id): Operation? = operations[id]
    override fun findAll(): List<Operation> = operations.values.toList()
    override fun save(entity: Operation): Boolean {
        operations[entity.id] = entity
        return true
    }
    override fun update(entity: Operation): Boolean {
        if (!operations.containsKey(entity.id)) return false
        operations[entity.id] = entity
        return true
    }
    override fun delete(id: Id): Boolean = operations.remove(id) != null
    override fun deleteByAccountId(accountId: String) {
        operations.values
            .filter { it.bankAccountId == accountId }
            .forEach { operations.remove(it.id) }
    }
    override fun deleteByCategoryId(categoryId: String) {
        operations.values
            .filter { it.categoryId == categoryId }
            .forEach { operations.remove(it.id) }
    }
}