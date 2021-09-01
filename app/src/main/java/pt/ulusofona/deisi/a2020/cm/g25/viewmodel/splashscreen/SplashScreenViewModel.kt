package pt.ulusofona.deisi.a2020.cm.g25.viewmodel.splashscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.AppDatabase
import pt.ulusofona.deisi.a2020.cm.g25.model.remote.RemoteData
import pt.ulusofona.deisi.a2020.cm.g25.model.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g25.model.repositories.DataRepository
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.SplashScreenCallbackInterface
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.SplashScreenDataFetcherInterface
import pt.ulusofona.deisi.a2020.cm.g25.model.logic.SplashScreenLogic
import pt.ulusofona.deisi.a2020.cm.g25.view.utils.Constants.Companion.BASE_URL

class SplashScreenViewModel(app: Application): AndroidViewModel(app) {

    private val storage = AppDatabase.getInstance(app).appDao()
    private var splashScreenDataFetcherInterface: SplashScreenDataFetcherInterface? = null
    val remoteData = RemoteData(storage, RetrofitBuilder.getInstance(BASE_URL), app)
    private val dataRepository = DataRepository(remoteData)
    private val splashScreenLogic = SplashScreenLogic(dataRepository)
    private lateinit var splashScreenCallbackInterface: SplashScreenCallbackInterface

    fun registerListener(splashScreenDataFetcherInterface: SplashScreenDataFetcherInterface) {
        this.splashScreenDataFetcherInterface = splashScreenDataFetcherInterface
        splashScreenCallbackInterface = object : SplashScreenCallbackInterface {
            override fun loadLastUpdateCompleted() {
                splashScreenLogic.getEntry(splashScreenCallbackInterface)
            }

            override fun loadGetEntryCompleted(){
                splashScreenLogic.getEntrySymptoms(splashScreenCallbackInterface)
            }

            override fun loadGetEntrySymptomsCompleted(){
                splashScreenLogic.getCounties(splashScreenCallbackInterface)
            }

            override fun returnConnectionError() {
                CoroutineScope(Dispatchers.Main).launch {
                    splashScreenDataFetcherInterface.onConnectionError()
                }

            }

            override fun returnTimeOutError() {
                CoroutineScope(Dispatchers.Main).launch {
                    splashScreenDataFetcherInterface.onTimeoutError()
                }
            }

            override fun loadGetCountiesCompleted() {
                splashScreenDataFetcherInterface.onDataLoadComplete()
            }

        }
    }

    fun loadData() {
        splashScreenLogic.getLastUpdate(splashScreenCallbackInterface)
    }
}