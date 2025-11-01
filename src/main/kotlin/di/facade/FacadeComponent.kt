package di.facade

import dagger.Subcomponent
import domain.facade.BankAccountFacade
import domain.facade.CategoryFacade
import domain.facade.OperationFacade

@Subcomponent
interface FacadeComponent {
    val bankAccountFacade: BankAccountFacade
    val categoryFacade: CategoryFacade
    val operationFacade: OperationFacade

    @Subcomponent.Factory
    interface Factory {
        fun create(): FacadeComponent
    }
}