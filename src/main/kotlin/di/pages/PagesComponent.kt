package di.pages

import dagger.Subcomponent
import presentation.pages.MainPage
import presentation.pages.OperationListPage

@Subcomponent
interface PagesComponent {
    val mainPage: MainPage
    val operationListPage: OperationListPage

    @Subcomponent.Factory
    interface Factory {
        fun create(): PagesComponent
    }
}