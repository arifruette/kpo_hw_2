package data.factory

import domain.factory.BankAccountFactory
import domain.models.BankAccount
import domain.models.common.IdGenerator
import javax.inject.Inject

class BankAccountFactoryImpl @Inject constructor(): BankAccountFactory {
    override fun createBankAccount(name: String, balance: Double): BankAccount {
        if (name.isBlank()) throw IllegalArgumentException("Account name cannot be blank")
        if (balance < 0) throw IllegalArgumentException("Balance cannot be negative")

        return BankAccount(
            id = IdGenerator.generateId(),
            name = name,
            balance = balance
        )
    }
}