package domain.command

interface Command {
    fun execute()
    val name: String
}