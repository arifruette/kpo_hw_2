package domain.models

import domain.models.common.Id
import java.time.LocalDateTime

data class Operation(
    val id: Id,
    var type: OperationType,
    var bankAccountId: String,
    var amount: Double,
    var date: LocalDateTime,
    var description: String?,
    var categoryId: String,
)