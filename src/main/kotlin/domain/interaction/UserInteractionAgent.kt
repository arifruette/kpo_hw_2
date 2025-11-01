package domain.interaction

interface UserInteractionAgent {
    fun showMessage(message: String)

    fun inputString(message: String, defaultValue: String = ""): String

    fun inputDouble(message: String, defaultValue: Double = 0.0): Double

    fun inputInt(message: String, defaultValue: Int = 0): Int

    fun waitForNextStep(): Boolean
}