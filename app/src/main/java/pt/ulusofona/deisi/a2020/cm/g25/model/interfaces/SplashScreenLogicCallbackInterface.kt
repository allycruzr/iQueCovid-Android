package pt.ulusofona.deisi.a2020.cm.g25.model.interfaces

import com.google.gson.internal.LinkedTreeMap
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.dao.AppDao
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.Symptoms

interface SplashScreenLogicCallbackInterface {
    fun lastUpdateReturn(response: CovidData)
    fun getEntryReturn(
        response: LinkedTreeMap<String, LinkedTreeMap<String, String?>>,
        storage: AppDao
    )
    fun getEntrySymptomsReturn(
        response: LinkedTreeMap<String, LinkedTreeMap<String, String?>>,
        storage: AppDao
    )

    fun last48HReturnDB(last48Hours: CovidData)
    fun symptomsReturnDB(symptoms: Symptoms)
    fun returnConnectionError()
    fun returnTimeOutError()
    fun getCountiesReturn()

}