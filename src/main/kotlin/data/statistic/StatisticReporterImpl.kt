package data.statistic

import domain.statistic.StatisticReporter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class StatisticReporterImpl @Inject constructor() : StatisticReporter {
    private val lines = mutableListOf<String>()

    override fun addLine(line: String) {
        lines.add("${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}: $line")
    }

    override fun getReport(): List<String> {
        return lines.toList()
    }

    override fun clear() {
        lines.clear()
    }
}