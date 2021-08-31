package pt.ulusofona.deisi.a2020.cm.g25.model.interfaces

interface SplashScreenDataFetcherInterface {
    fun onDataLoadComplete()
    fun onConnectionError()
    fun onTimeoutError()
}