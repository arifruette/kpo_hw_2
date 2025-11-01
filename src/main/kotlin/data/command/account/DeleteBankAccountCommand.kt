package data.command.account

import domain.command.Command
import domain.facade.BankAccountFacade
import domain.interaction.UserInteractionAgent
import javax.inject.Inject

// Команда удаления счета
class DeleteBankAccountCommand @Inject constructor(
    private val bankAccountFacade: BankAccountFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override val name: String = "delete_bank_account"
    
    override fun execute() {
        val accounts = bankAccountFacade.getAllBankAccounts()
        
        if (accounts.isEmpty()) {
            userInteractionAgent.showMessage("Нет счетов для удаления")
            return
        }
        
        userInteractionAgent.showMessage("Выберите счет для удаления:")
        accounts.forEachIndexed { index, account ->
            userInteractionAgent.showMessage("${index + 1}. ${account.name} - Баланс: ${account.balance} ₽")
        }
        
        val index = userInteractionAgent.inputInt("", 1) - 1
        if (index !in accounts.indices) {
            userInteractionAgent.showMessage("Некорректный выбор")
            return
        }
        
        val account = accounts[index]
        val confirm = userInteractionAgent.inputBoolean("Вы уверены, что хотите удалить счет '${account.name}'?")
        
        if (confirm) {
            val success = bankAccountFacade.deleteBankAccountById(account.id)
            if (success) {
                userInteractionAgent.showMessage("Счет успешно удален")
            } else {
                userInteractionAgent.showMessage("Ошибка при удалении счета")
            }
        } else {
            userInteractionAgent.showMessage("Удаление отменено")
        }
    }
}