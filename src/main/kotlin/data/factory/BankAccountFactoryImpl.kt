package data.factory

import domain.factory.BankAccountFactory
import domain.models.BankAccount
import domain.models.common.IdGenerator

class BankAccountFactoryImpl : BankAccountFactory {
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