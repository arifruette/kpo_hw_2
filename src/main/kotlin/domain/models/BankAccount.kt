package domain.models

import domain.models.common.Id

data class BankAccount(
    val id: Id,
    var name: String,
    var balance: Double
)