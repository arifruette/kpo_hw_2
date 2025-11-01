package di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ShowBankAccounts

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CreateBankAccount

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UpdateBankAccount

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DeleteBankAccount