package domain.facade

import domain.models.BankAccount
import domain.models.common.Id
import domain.visitor.BaseElement

interface BankAccountFacade: BaseElement {

    fun addExistingBankAccount(bankAccount: BankAccount): Boolean

    fun deleteBankAccountById(id: Id): Boolean

    fun addBankAccount(name: String, balance: Double): BankAccount

    fun updateBankAccountInfoById(id: Id, name: String?, balance: Double?): BankAccount

    fun getBankAccountById(id: Id): BankAccount?

    fun getAllBankAccounts(): List<BankAccount>
}