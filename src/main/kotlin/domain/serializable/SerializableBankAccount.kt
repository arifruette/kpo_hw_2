package domain.serializable

import domain.models.BankAccount
import domain.models.common.toId

@kotlinx.serialization.Serializable
data class SerializableBankAccount(
    val id: String,
    val name: String,
    val balance: Double
) {
    fun toDomain(): BankAccount = BankAccount(id.toId(), name, balance)
}


