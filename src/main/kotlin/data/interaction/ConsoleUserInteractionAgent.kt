package data.interaction

import domain.interaction.UserInteractionAgent
import javax.inject.Inject

class ConsoleUserInteractionAgent @Inject constructor(): UserInteractionAgent {
    override fun showMessage(message: String) {
        println(message)
    }
    
    override fun inputString(message: String, defaultValue: String): String {
        print("$message ")
        val input = readlnOrNull()?.trim() ?: ""
        return input.ifBlank { defaultValue }
    }
    
    override fun inputDouble(message: String, defaultValue: Double): Double {
        print("$message ")
        val input = readlnOrNull()?.trim()?.toDoubleOrNull()
        return input ?: defaultValue
    }
    
    override fun inputInt(message: String, defaultValue: Int): Int {
        print("$message ")
        val input = readlnOrNull()?.trim()?.toIntOrNull()
        return input ?: defaultValue
    }

    override fun inputBoolean(message: String): Boolean {
        while (true) {
            print("$message (y/n) ")
            val input = readLine()?.trim()?.lowercase()
            when (input) {
                "y", "yes", "да", "д" -> return true
                "n", "no", "нет", "н" -> return false
                else -> println("Пожалуйста, введите 'y' или 'n'")
            }
        }
    }

    override fun waitForNextStep(): Boolean {
        print("Нажмите Enter для продолжения...")
        readlnOrNull()
        return true
    }
}