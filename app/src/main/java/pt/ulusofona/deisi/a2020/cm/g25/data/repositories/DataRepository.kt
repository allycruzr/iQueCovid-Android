package pt.ulusofona.deisi.a2020.cm.g25.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.RemoteData
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.SplashScreenLogicCallbackInterface

@Suppress("DEPRECATION")
class DataRepository(val remoteData: RemoteData) {

    fun networkAvailable(): Boolean {
        val connectivityManager =
            remoteData.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun getLastUpdate(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        if (networkAvailable()) {
            remoteData.getLastUpdateFromAPI(splashScreenLogicCallbackInterface)
        } else {
            remoteData.getLastUpdateFromDB(splashScreenLogicCallbackInterface)
        }
    }

    fun getCounties(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        if (networkAvailable()) {
            remoteData.getCountiesWeb(splashScreenLogicCallbackInterface)
        } else {
            splashScreenLogicCallbackInterface.getCountiesReturn()
        }

    }

    fun getEntryFromDate(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        if (networkAvailable()) {
            remoteData.getEntryFromDateWeb(splashScreenLogicCallbackInterface)
        } else {
            remoteData.getEntryFromDateDB(splashScreenLogicCallbackInterface)
        }
    }
}