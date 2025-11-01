package data.statistic

import domain.statistic.StatisticReporter
import java.time.LocalDateTime
import javax.inject.Inject

class StatisticReporterImpl @Inject constructor(): StatisticReporter {
    private val lines = mutableListOf<String>()
    
    override fun addLine(line: String) {
        lines.add("${LocalDateTime.now()}: $line")
    }
    
    override fun getReport(): List<String> {
        return lines.toList()
    }
    
    override fun clear() {
        lines.clear()
    }
}