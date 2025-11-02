package domain.serializable

import domain.models.Operation
import domain.models.OperationType
import domain.models.common.toId
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SerializableOperation(
    val id: String,
    val type: String,
    val bankAccountId: String,
    val amount: Double,
    val date: String,
    val description: String?,
    val categoryId: String
) {
    fun toDomain(): Operation = Operation(
        id = id.toId(),
        type = OperationType.valueOf(type),
        bankAccountId = bankAccountId,
        amount = amount,
        date = LocalDateTime.parse(date),
        description = description,
        categoryId = categoryId
    )
}