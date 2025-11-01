package di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class ShowOperations

@Qualifier
@Retention
annotation class CreateOperation

@Qualifier
@Retention
annotation class DeleteOperation

@Qualifier
@Retention
annotation class UpdateOperation