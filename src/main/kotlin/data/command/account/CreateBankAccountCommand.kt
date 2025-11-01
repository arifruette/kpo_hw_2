package data.command.account

import domain.command.Command
import domain.facade.BankAccountFacade
import domain.interaction.UserInteractionAgent
import javax.inject.Inject

// Команда создания счета
class CreateBankAccountCommand @Inject constructor(
    private val bankAccountFacade: BankAccountFacade,
    private val userInteractionAgent: UserInteractionAgent
) : Command {
    
    override val name: String = "create_bank_account"
    
    override fun execute() {
        val name = userInteractionAgent.inputString("Введите название счета:")
        val balance = userInteractionAgent.inputDouble("Введите начальный баланс:")
        
        try {
            val account = bankAccountFacade.addBankAccount(name, balance)
            userInteractionAgent.showMessage("Счет '${account.name}' успешно создан!")
        } catch (e: Exception) {
            userInteractionAgent.showMessage("Ошибка при создании счета: ${e.message}")
        }
    }
}
