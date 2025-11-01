package domain.statistic

interface StatisticReporter {
    fun addLine(line: String)
    fun getReport(): List<String>
    fun clear()
}
