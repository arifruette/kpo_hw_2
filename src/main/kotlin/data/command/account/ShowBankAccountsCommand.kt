package data.command.account

import domain.command.Command
import domain.facade.BankAccountFacade
import domain.interaction.UserInteractionAgent

// Команда показа счетов
class ShowBankAccountsCommand(
    private val bankAccountFacade: BankAccountFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override val name: String = "show_bank_accounts"
    
    override fun execute() {
        val accounts = bankAccountFacade.getAllBankAccounts()
        
        if (accounts.isEmpty()) {
            userInteractionAgent.showMessage("Счетов нет")
        } else {
            userInteractionAgent.showMessage("Список счетов")
            accounts.forEachIndexed { index, account ->
                userInteractionAgent.showMessage("${index + 1}. ${account.name} - Баланс: ${account.balance} ₽")
            }
        }
    }
}