package di.visitor

import dagger.Binds
import dagger.Module
import data.visitor.CsvVisitorImpl
import data.visitor.JsonVisitorImpl
import di.qualifiers.visitor.CsvVisitor
import di.qualifiers.visitor.JsonVisitor
import domain.visitor.Visitor
import javax.inject.Singleton

// VisitorModule.kt
@Module
interface VisitorModule {

    @Binds
    @Singleton
    @CsvVisitor
    fun provideCsvVisitor(visitor: CsvVisitorImpl): Visitor

    @Binds
    @Singleton
    @JsonVisitor
    fun provideJsonVisitor(visitor: JsonVisitorImpl): Visitor
}