import di.DaggerAppComponent

fun main() {
    val appComponent = DaggerAppComponent.create()
    val pagesComponent = appComponent.pagesComponent().create()
    val renderer = pagesComponent.defaultPageRenderer

    renderer.startRendering()
}