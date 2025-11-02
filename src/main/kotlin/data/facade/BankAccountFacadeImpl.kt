package data.facade

import domain.facade.BankAccountFacade
import domain.factory.BankAccountFactory
import domain.models.BankAccount
import domain.models.common.Id
import domain.repository.BankAccountRepository
import domain.repository.OperationRepository
import domain.visitor.Visitor
import javax.inject.Inject

class BankAccountFacadeImpl @Inject constructor(
    private val bankAccountRepository: BankAccountRepository,
    private val operationRepository: OperationRepository,
    private val bankAccountFactory: BankAccountFactory
) : BankAccountFacade {

    override fun addExistingBankAccount(bankAccount: BankAccount): Boolean {
        if (bankAccountRepository.findByName(bankAccount.name) != null) return false
        return bankAccountRepository.save(bankAccount)
    }

    override fun deleteBankAccountById(id: Id): Boolean {
        val account = bankAccountRepository.findById(id) ?: return false
        operationRepository.deleteByAccountId(account.id.raw)
        return bankAccountRepository.delete(id)
    }

    override fun addBankAccount(name: String, balance: Double): BankAccount {
        if (bankAccountRepository.findByName(name) != null) {
            throw IllegalArgumentException("Account with name '$name' already exists")
        }
        val account = bankAccountFactory.createBankAccount(name, balance)
        bankAccountRepository.save(account)
        return account
    }

    override fun updateBankAccountInfoById(id: Id, name: String?, balance: Double?): BankAccount {
        val existing = bankAccountRepository.findById(id) ?: throw IllegalArgumentException("Account not found")

        name?.let { newName ->
            val existingWithSameName = bankAccountRepository.findByName(newName)
            if (existingWithSameName != null && existingWithSameName.id != id) {
                throw IllegalArgumentException("Account with name '$newName' already exists")
            }
            existing.name = newName
        }

        balance?.let {
            if (it < 0) throw IllegalArgumentException("Balance cannot be negative")
            existing.balance = it
        }

        bankAccountRepository.update(existing)
        return existing
    }

    override fun getBankAccountById(id: Id): BankAccount? = bankAccountRepository.findById(id)

    override fun getAllBankAccounts(): List<BankAccount> = bankAccountRepository.findAll()

    override fun acceptVisitor(visitor: Visitor) = visitor.processBankAccounts(bankAccountRepository.findAll())
}