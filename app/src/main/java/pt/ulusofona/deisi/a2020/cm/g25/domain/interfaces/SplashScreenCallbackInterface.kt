package pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces

interface SplashScreenCallbackInterface {
    fun loadLastUpdateCompleted()
    fun loadGetEntryCompleted()
    fun returnConnectionError()
    fun returnTimeOutError()
    fun loadGetCountiesCompleted()
}
