package di.repository

import dagger.Subcomponent
import domain.repository.BankAccountRepository
import domain.repository.CategoryRepository
import domain.repository.OperationRepository

@Subcomponent
interface RepositoryComponent {
    val bankAccountRepository: BankAccountRepository
    val categoryRepository: CategoryRepository
    val operationRepository: OperationRepository

    @Subcomponent.Factory
    interface Factory {
        fun create(): RepositoryComponent
    }
}