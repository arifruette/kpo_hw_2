package di.command.models

import CreateCategoryCommand
import dagger.Module
import dagger.Provides
import data.command.TimeComputingCommandDecorator
import data.command.category.DeleteCategoryCommand
import data.command.category.EditCategoryCommand
import data.command.category.ShowCategoriesCommand
import di.qualifiers.models.CreateExpenseCategory
import di.qualifiers.models.CreateIncomeCategory
import di.qualifiers.models.DeleteCategory
import di.qualifiers.models.EditCategory
import di.qualifiers.models.ShowCategories
import domain.command.Command
import domain.facade.CategoryFacade
import domain.interaction.UserInteractionAgent
import domain.models.CategoryType
import domain.statistic.StatisticReporter

@Module
object CategoriesCommandModule {
    //region category commands
    @Provides
    @ShowCategories
    fun provideShowCategoriesCommand(
        categoryFacade: CategoryFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = ShowCategoriesCommand(categoryFacade, userInteractionAgent),
            statisticReporter = statisticReporter
        )
    }

    @Provides
    @CreateIncomeCategory
    fun provideCreateIncomeCategoryCommand(
        categoryFacade: CategoryFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = CreateCategoryCommand(
                categoryFacade = categoryFacade,
                userInteractionAgent = userInteractionAgent,
                categoryType = CategoryType.INCOME
            ),
            statisticReporter = statisticReporter
        )
    }

    @Provides
    @CreateExpenseCategory
    fun provideCreateExpenseCategoryCommand(
        categoryFacade: CategoryFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = CreateCategoryCommand(
                categoryFacade = categoryFacade,
                userInteractionAgent = userInteractionAgent,
                categoryType = CategoryType.EXPENSE
            ),
            statisticReporter = statisticReporter
        )
    }

    @Provides
    @EditCategory
    fun provideEditCategoryCommand(
        categoryFacade: CategoryFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = EditCategoryCommand(categoryFacade, userInteractionAgent),
            statisticReporter = statisticReporter
        )
    }

    @Provides
    @DeleteCategory
    fun provideDeleteCategoryCommand(
        categoryFacade: CategoryFacade,
        userInteractionAgent: UserInteractionAgent,
        statisticReporter: StatisticReporter
    ): Command {
        return TimeComputingCommandDecorator(
            baseCommand = DeleteCategoryCommand(categoryFacade, userInteractionAgent),
            statisticReporter = statisticReporter
        )
    }
    //endregion
}