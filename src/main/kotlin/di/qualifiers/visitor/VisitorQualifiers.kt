package di.qualifiers.visitor

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CsvVisitor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonVisitor
