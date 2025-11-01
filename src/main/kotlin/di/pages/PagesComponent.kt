package di.pages

import dagger.Subcomponent
import presentation.DefaultPageRenderer

@Subcomponent
interface PagesComponent {
    val defaultPageRenderer: DefaultPageRenderer

    @Subcomponent.Factory
    interface Factory {
        fun create(): PagesComponent
    }
}