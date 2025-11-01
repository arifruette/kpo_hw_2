package data.factory

import domain.factory.OperationFactory
import domain.models.Operation
import domain.models.OperationType
import domain.models.common.IdGenerator
import java.time.LocalDateTime
import javax.inject.Inject

class OperationFactoryImpl @Inject constructor(): OperationFactory {
    override fun createOperation(
        type: OperationType,
        bankAccountId: String,
        amount: Double,
        date: LocalDateTime,
        description: String?,
        categoryId: String
    ): Operation {
        if (amount <= 0) throw IllegalArgumentException("Operation amount must be positive")
        if (bankAccountId.isBlank()) throw IllegalArgumentException("Bank account ID cannot be blank")
        if (categoryId.isBlank()) throw IllegalArgumentException("Category ID cannot be blank")
        if (date.isAfter(LocalDateTime.now())) throw IllegalArgumentException("Operation date cannot be in the future")

        return Operation(
            id = IdGenerator.generateId(),
            type = type,
            bankAccountId = bankAccountId,
            amount = amount,
            date = date,
            description = description,
            categoryId = categoryId
        )
    }
}