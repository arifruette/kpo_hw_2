package presentation.pages

import di.qualifiers.CreateBankAccount
import di.qualifiers.DeleteBankAccount
import di.qualifiers.ShowBankAccounts
import di.qualifiers.UpdateBankAccount
import domain.command.Command
import domain.interaction.UserInteractionAgent
import presentation.Page
import presentation.contract.FinishRenderResult
import javax.inject.Inject

class BankAccountListPage @Inject constructor(
    override val userInteractionAgent: UserInteractionAgent,
    @ShowBankAccounts private val showBankAccountsCommand: Command,
    @CreateBankAccount private val createBankAccountCommand: Command,
    @UpdateBankAccount private val updateBankAccountCommand: Command,
    @DeleteBankAccount private val deleteBankAccountCommand: Command
) : Page() {
    
    override fun render() {
        userInteractionAgent.showMessage("=== Управление счетами ===")
        showBankAccountsCommand.execute()
        userInteractionAgent.showMessage("\nДействия:")
        userInteractionAgent.showMessage("1. Создать счет")
        userInteractionAgent.showMessage("2. Редактировать счет")
        userInteractionAgent.showMessage("3. Удалить счет")
        userInteractionAgent.showMessage("4. Назад")
    }

    override fun finishRendering(): FinishRenderResult {
        return when (userInteractionAgent.inputInt("Выберите действие:", 0)) {
            1 -> {
                createBankAccountCommand.execute()
                FinishRenderResult.Pop
            }
            2 -> {
                updateBankAccountCommand.execute()
                FinishRenderResult.Pop
            }
            3 -> {
                deleteBankAccountCommand.execute()
                FinishRenderResult.Pop
            }
            4 -> FinishRenderResult.Pop
            else -> FinishRenderResult.Pop
        }
    }
}