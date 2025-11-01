package data.command

import domain.command.Command
import domain.statistic.StatisticReporter

class TimeComputingCommandDecorator(
    private val baseCommand: Command,
    private val statisticReporter: StatisticReporter
) : Command {
    
    override val name: String = baseCommand.name
    
    override fun execute() {
        val startTime = System.currentTimeMillis()
        baseCommand.execute()
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        statisticReporter.addLine("Команда '${baseCommand.name}' выполнена за ${duration}ms")
    }
}