package domain.repository

import domain.models.BankAccount

interface BankAccountRepository : Repository<BankAccount> {
    fun findByName(name: String): BankAccount?
}