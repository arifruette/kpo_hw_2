package di.statistic

import dagger.Subcomponent
import domain.statistic.StatisticReporter

@Subcomponent
interface StatisticsComponent {
    val statisticReporter: StatisticReporter
}