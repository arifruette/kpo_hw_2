package di.facade

import dagger.Module
import dagger.Provides
import data.facade.BankAccountFacadeImpl
import data.facade.CategoryFacadeImpl
import data.facade.OperationFacadeImpl
import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade
import domain.factory.BankAccountFactory
import domain.factory.CategoryFactory
import domain.factory.OperationFactory
import domain.repository.BankAccountRepository
import domain.repository.CategoryRepository
import domain.repository.OperationRepository
import javax.inject.Singleton

@Module
object FacadeModule {
    @Provides
    @Singleton
    fun provideBankAccountFacade(
        repository: BankAccountRepository,
        operationRepository: OperationRepository,
        factory: BankAccountFactory
    ): BankAccountFacade {
        return BankAccountFacadeImpl(repository, operationRepository, factory)
    }

    @Provides
    @Singleton
    fun provideCategoryFacade(
        repository: CategoryRepository,
        operationRepository: OperationRepository,
        factory: CategoryFactory
    ): CategoryFacade {
        return CategoryFacadeImpl(repository, operationRepository, factory)
    }

    @Provides
    @Singleton
    fun provideOperationFacade(
        repository: OperationRepository,
        bankAccountRepository: BankAccountRepository,
        categoryRepository: CategoryRepository,
        factory: OperationFactory
    ): OperationFacade {
        return OperationFacadeImpl(repository, bankAccountRepository, categoryRepository, factory)
    }
}
