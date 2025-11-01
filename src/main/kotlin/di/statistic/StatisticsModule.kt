package di.statistic

import dagger.Binds
import dagger.Module
import data.statistic.StatisticReporterImpl
import domain.statistic.StatisticReporter
import javax.inject.Singleton

@Module
interface StatisticsModule {
    @Binds
    @Singleton
    fun bindStatisticsReporter(statisticReporter: StatisticReporterImpl): StatisticReporter
}