package pt.ulusofona.deisi.a2020.cm.g25.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.RemoteData
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.SplashScreenLogicCallbackInterface

@Suppress("DEPRECATION")
class DataRepository(val remoteData: RemoteData) {

    private lateinit var splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface

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

    fun getCounties(splashScreenLogicReturnInterface: SplashScreenLogicCallbackInterface) {
        if (networkAvailable()) {
            remoteData.getCountiesWeb(splashScreenLogicReturnInterface)
        } else {
            splashScreenLogicReturnInterface.getCountiesReturn()
        }

    }

    fun getEntryFromDate(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        if (networkAvailable()) {
            remoteData.getEntryFromDateWeb(splashScreenLogicCallbackInterface)
        } else {
            remoteData.getEntryFromDateDB(splashScreenLogicCallbackInterface)
        }
    }


    /** For tests **/

//    private fun notifyOnDataLoaded(){
//        listener?.onDataRepositoryLoad(data)
//    }
//
//    fun registerListener(listener: OnDataRepositoryLoad){
//        this.listener = listener
//        getCovidData()
//        listener?.onDataRepositoryLoad(data)
//    }
//
//    fun unregisterListener(){
//        listener = null
//    }
//
//    fun getCovidData(){
//        CoroutineScope(Dispatchers.IO).launch {
//            var loadable = false
//            try{
//                data_io = RemoteData.getCovidData()
//                loadable = true
//                if(data_io.confirmados == 0){
//                    val obj = local.getLatest("1").toCovidData()
//                    if(obj != null) {
//                        data_io.confirmados = obj.confirmados
//                        data_io.confirmados_alentejo = obj.confirmados_alentejo
//                        data_io.confirmados_algarve = obj.confirmados_algarve
//                        data_io.confirmados_centro = obj.confirmados_centro
//                        data_io.confirmados_lvt = obj.confirmados_lvt
//                        data_io.confirmados_madeira = obj.confirmados_madeira
//                        data_io.confirmados_norte = obj.confirmados_norte
//                        data_io.confirmados_novos = obj.confirmados_novos
//                        data_io.confirmados_scores = obj.confirmados_scores
//                    }
//                }
//            } catch (e:Exception){
//
//            }
//        }
//    }


}