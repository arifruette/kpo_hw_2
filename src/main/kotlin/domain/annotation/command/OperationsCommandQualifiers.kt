package domain.annotation.command

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class ShowOperations

@Qualifier
@Retention
annotation class CreateOperations

@Qualifier
@Retention
annotation class DeleteOperations