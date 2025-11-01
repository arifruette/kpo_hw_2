import di.DaggerAppComponent

fun main() {
    val appComponent = DaggerAppComponent.create()
    val pagesComponent = appComponent.pagesComponent().create()
    val renderer = pagesComponent.defaultPageRenderer

    renderer.startRendering()
}


//interface StatisticReporter {
//    fun addLine(line: String)
//}
//class TimeComputingCommandDecorator(
//    private val baseCommand: Command,
//    private val statisticReporter: StatisticReporter
//): Command {
//    override fun execute() {
//        val curTime = System.currentTimeMillis()
//        baseCommand.execute()
//        val endTime = System.currentTimeMillis()
//        statisticReporter.addLine("Операция ${baseCommand.name} была выполнена за ${endTime - curTime}")
//    }
//
//}