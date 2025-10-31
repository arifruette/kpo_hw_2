package domain.factory

import domain.models.BankAccount

interface BankAccountFactory {
    fun createBankAccount(name: String, balance: Double): BankAccount
}