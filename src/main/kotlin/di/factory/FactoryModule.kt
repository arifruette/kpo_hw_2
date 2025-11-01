package di.factory

import dagger.Module
import dagger.Provides
import data.factory.BankAccountFactoryImpl
import data.factory.CategoryFactoryImpl
import data.factory.OperationFactoryImpl
import domain.factory.BankAccountFactory
import domain.factory.CategoryFactory
import domain.factory.OperationFactory
import javax.inject.Singleton

@Module
object FactoryModule {
    @Provides
    @Singleton
    fun provideBankAccountFactory(): BankAccountFactory = BankAccountFactoryImpl()

    @Provides
    @Singleton
    fun provideCategoryFactory(): CategoryFactory = CategoryFactoryImpl()

    @Provides
    @Singleton
    fun provideOperationFactory(): OperationFactory = OperationFactoryImpl()
}