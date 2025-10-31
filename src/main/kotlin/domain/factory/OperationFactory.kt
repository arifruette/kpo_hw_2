package domain.factory

import domain.models.Operation
import domain.models.OperationType
import java.time.LocalDateTime

interface OperationFactory {
    fun createOperation(
        type: OperationType,
        bankAccountId: String,
        amount: Double,
        date: LocalDateTime,
        description: String?,
        categoryId: String,
    ): Operation
}