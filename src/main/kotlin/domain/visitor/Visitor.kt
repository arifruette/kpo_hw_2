package domain.visitor

import domain.models.BankAccount
import domain.models.Category
import domain.models.Operation

interface Visitor {
    fun processBankAccounts(bankAccounts: List<BankAccount>): String
    fun processCategories(categories: List<Category>): String
    fun processOperations(operations: List<Operation>): String
}