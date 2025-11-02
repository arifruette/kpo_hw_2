package di.qualifiers.models

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ShowCategories

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CreateIncomeCategory

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CreateExpenseCategory

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EditCategory

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DeleteCategory