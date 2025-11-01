package data.command.account

import domain.command.Command
import domain.facade.BankAccountFacade
import domain.interaction.UserInteractionAgent

// Команда обновления счета
class UpdateBankAccountCommand(
    private val bankAccountFacade: BankAccountFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override val name: String = "update_bank_account"
    
    override fun execute() {
        val accounts = bankAccountFacade.getAllBankAccounts()
        
        if (accounts.isEmpty()) {
            userInteractionAgent.showMessage("Нет счетов для редактирования")
            return
        }
        
        userInteractionAgent.showMessage("Выберите счет для редактирования:")
        accounts.forEachIndexed { index, account ->
            userInteractionAgent.showMessage("${index + 1}. ${account.name} - Баланс: ${account.balance} ₽")
        }
        
        val index = userInteractionAgent.inputInt("", 1) - 1
        if (index !in accounts.indices) {
            userInteractionAgent.showMessage("Некорректный выбор")
            return
        }
        
        val account = accounts[index]
        userInteractionAgent.showMessage("Редактирование счета '${account.name}':")
        
        val newName = userInteractionAgent.inputString("Введите новое название (оставьте пустым чтобы не менять):", "")
        val newBalanceStr = userInteractionAgent.inputString("Введите новый баланс (оставьте пустым чтобы не менять):", "")
        
        val nameToSet = newName.ifBlank { null }
        val balanceToSet = newBalanceStr.toDoubleOrNull()
        
        try {
            val updatedAccount = bankAccountFacade.updateBankAccountInfoById(
                account.id, nameToSet, balanceToSet
            )
            userInteractionAgent.showMessage("Счет '${updatedAccount.name}' успешно обновлен!")
        } catch (e: Exception) {
            userInteractionAgent.showMessage("Ошибка при обновлении счета: ${e.message}")
        }
    }
}