package domain.repository

import domain.models.Operation

interface OperationRepository : Repository<Operation> {
    fun deleteByAccountId(accountId: String)
    fun deleteByCategoryId(categoryId: String)
}