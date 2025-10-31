package data.repository

import domain.models.BankAccount
import domain.models.common.Id
import domain.repository.BankAccountRepository

class BankAccountRepositoryImpl : BankAccountRepository {
    private val accounts = mutableMapOf<Id, BankAccount>()
    
    override fun findById(id: Id): BankAccount? = accounts[id]
    override fun findAll(): List<BankAccount> = accounts.values.toList()
    override fun save(entity: BankAccount): Boolean {
        accounts[entity.id] = entity
        return true
    }
    override fun update(entity: BankAccount): Boolean {
        if (!accounts.containsKey(entity.id)) return false
        accounts[entity.id] = entity
        return true
    }
    override fun delete(id: Id): Boolean = accounts.remove(id) != null
    override fun findByName(name: String): BankAccount? = 
        accounts.values.find { it.name == name }
}