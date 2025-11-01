package di.factory

import dagger.Subcomponent
import domain.factory.BankAccountFactory
import domain.factory.CategoryFactory
import domain.factory.OperationFactory

@Subcomponent
interface FactoryComponent {
    val bankAccountFactory: BankAccountFactory
    val categoryFactory: CategoryFactory
    val operationFactory: OperationFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(): FactoryComponent
    }
}