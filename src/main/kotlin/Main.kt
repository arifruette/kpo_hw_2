import di.DaggerAppComponent
import presentation.DefaultPageRenderer
import presentation.Page
import presentation.pages.OperationListPage
import kotlin.reflect.KClass

fun main() {
    val appComponent = DaggerAppComponent.create()
    val pagesComponent = appComponent.pagesComponent().create()


    val screens: MutableMap<KClass<out Page>, Page> = mutableMapOf()
    val mainPage = pagesComponent.mainPage

    screens[mainPage::class] = mainPage
    screens[OperationListPage::class] = pagesComponent.operationListPage

    val renderer = DefaultPageRenderer(screens, mainPage)

    renderer.startRendering()
}