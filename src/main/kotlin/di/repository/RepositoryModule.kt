package di.repository

import dagger.Binds
import dagger.Module
import data.repository.BankAccountRepositoryImpl
import data.repository.CategoryRepositoryImpl
import data.repository.OperationRepositoryImpl
import domain.repository.BankAccountRepository
import domain.repository.CategoryRepository
import domain.repository.OperationRepository
import javax.inject.Singleton

@Module
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindBankAccountRepository(impl: BankAccountRepositoryImpl): BankAccountRepository

    @Binds
    @Singleton  
    fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    fun bindOperationRepository(impl: OperationRepositoryImpl): OperationRepository
}