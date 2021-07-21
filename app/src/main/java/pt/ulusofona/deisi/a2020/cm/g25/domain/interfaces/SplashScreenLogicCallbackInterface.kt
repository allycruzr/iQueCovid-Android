package pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces

import com.google.gson.internal.LinkedTreeMap
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao.AppDao
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidData

interface SplashScreenLogicCallbackInterface {
    fun lastUpdateReturn(response: CovidData)
    fun getEntryReturn(
        response: LinkedTreeMap<String, LinkedTreeMap<String, String?>>,
        storage: AppDao
    )

    fun last48HReturnDB(last48Hours: CovidData)
    fun returnConnectionError()
    fun returnTimeOutError()
    fun getCountiesReturn()

}