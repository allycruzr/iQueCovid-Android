package pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels.splashscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.AppDatabase
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.RemoteData
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g25.data.repositories.DataRepository
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.SplashScreenCallbackInterface
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.SplashScreenDataFetcherInterface
import pt.ulusofona.deisi.a2020.cm.g25.domain.logic.SplashScreenLogic
import pt.ulusofona.deisi.a2020.cm.g25.ui.utils.Constants.Companion.BASE_URL

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
                splashScreenLogic.getCounties(splashScreenCallbackInterface)
            }

            override fun returnConnectionError() {
                CoroutineScope(Dispatchers.Main).launch {
                    splashScreenCallbackInterface.returnConnectionError()
                }

            }

            override fun returnTimeOutError() {
                CoroutineScope(Dispatchers.Main).launch {
                    splashScreenCallbackInterface.returnTimeOutError()
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