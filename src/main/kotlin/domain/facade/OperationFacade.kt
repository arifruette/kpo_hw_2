package domain.facade

import domain.models.Operation
import domain.models.OperationType
import domain.models.common.Id
import java.time.LocalDateTime

interface OperationFacade {

    fun addOperation(
        type: OperationType,
        bankAccountId: String,
        amount: Double,
        date: LocalDateTime,
        description: String?,
        categoryId: String,
    ): Boolean

    fun getOperationById(id: Id): Operation?

    fun updateOperationById(
        id: Id,
        type: OperationType?,
        bankAccountId: String?,
        amount: Double?,
        date: LocalDateTime?,
        description: String?,
        categoryId: String?,
    ): Boolean

    fun deleteOperationById(id: Id): Boolean

    fun addExistingOperation(operation: Operation): Boolean

    fun getAllOperations(): List<Operation>
}