package pt.ulusofona.deisi.a2020.cm.g25.domain.logic

import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao.AppDao
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g25.data.repositories.DataRepository
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.SplashScreenCallbackInterface
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.SplashScreenLogicCallbackInterface

class SplashScreenLogic(val repository: DataRepository) {

    private lateinit var splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface

    fun getLastUpdate(splashScreenCallbackInterface: SplashScreenCallbackInterface){
        createInterface(splashScreenCallbackInterface)
        repository.getLastUpdate(splashScreenLogicCallbackInterface)
    }

    fun getEntry(splashScreenReturnInterface: SplashScreenCallbackInterface) {
        createInterface(splashScreenReturnInterface)
        repository.getEntryFromDate(splashScreenLogicCallbackInterface)
    }
    fun getCounties(splashScreenReturnInterface: SplashScreenCallbackInterface) {
        createInterface(splashScreenReturnInterface)
        repository.getCounties(splashScreenLogicCallbackInterface)
    }

    private fun createInterface(splashScreenCallbackInterface: SplashScreenCallbackInterface) {
        splashScreenLogicCallbackInterface = object : SplashScreenLogicCallbackInterface {


            override fun lastUpdateReturn(response: CovidData) {
                val dataSource = DataSource.getInstance()
                dataSource.addLast24H(response)
                splashScreenCallbackInterface.loadLastUpdateCompleted()
            }

            override fun getEntryReturn(
                response: LinkedTreeMap<String, LinkedTreeMap<String, String?>>,
                storage: AppDao
            ) {
                val dataSource = DataSource.getInstance()
                for(key in response.keys){
                    when (key){
                        "data" -> dataSource.dataLast48hours.dateOfData = cleanDataFromMap(response[key]?.values!!).toString()
                        "data_dados" -> dataSource.dataLast48hours.dateOfData = cleanDataFromMap(response[key]?.values!!).toString()
                        "confirmados" -> dataSource.dataLast48hours.confirmed = cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "confirmados_novos" -> dataSource.dataLast48hours.newlyConfirmed =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "recuperados" -> dataSource.dataLast48hours.recovered =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "obitos" -> dataSource.dataLast48hours.deaths =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "confirmados_arsnorte" -> dataSource.dataLast48hours.confirmedNorth =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "confirmados_arscentro" -> dataSource.dataLast48hours.confirmedCenter =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "confirmados_arslvt" -> dataSource.dataLast48hours.confirmedLvt =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "confirmados_arsalentejo" -> dataSource.dataLast48hours.confirmedAlentejo =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "confirmados_arsalgarve" -> dataSource.dataLast48hours.confirmedAlgarve =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "confirmados_acores" -> dataSource.dataLast48hours.confirmedAzores =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "confirmados_madeira" -> dataSource.dataLast48hours.confirmedMadeira =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "obitos_arsnorte" -> dataSource.dataLast48hours.deathsNorth =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "obitos_arscentro" -> dataSource.dataLast48hours.deathsCenter =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "obitos_arslvt" -> dataSource.dataLast48hours.deathsLvt =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "obitos_arsalentejo" -> dataSource.dataLast48hours.deathsAlentejo =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "obitos_arsalgarve" -> dataSource.dataLast48hours.deathsAlgarve =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "obitos_acores" -> dataSource.dataLast48hours.deathsAzores =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "obitos_madeira" -> dataSource.dataLast48hours.deathsMadeira =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "recuperados_arsnorte" -> dataSource.dataLast48hours.recoveredNorth =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "recuperados_arscentro" -> dataSource.dataLast48hours.recoveredCenter =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "recuperados_arslvt" -> dataSource.dataLast48hours.recoveredLvt =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "recuperados_arsalentejo" -> dataSource.dataLast48hours.recoveredAlentejo =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "recuperados_arsalgarve" -> dataSource.dataLast48hours.recoveredAlgarve =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "recuperados_acores" -> dataSource.dataLast48hours.recoveredAzores =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                        "recuperados_madeira" -> dataSource.dataLast48hours.recoveredMadeira =cleanDataFromMap(response[key]?.values!!).toString().toInt()
                    }
                }
                CoroutineScope(Dispatchers.IO).launch {
                    dataSource.dataLast48hours.uuid="48h"
                    storage.insertData(dataSource.dataLast48hours)
                }


                splashScreenCallbackInterface.loadGetEntryCompleted()
            }

            override fun last48HReturnDB(last48H: CovidData) {
                val dataSource = DataSource.getInstance()
                dataSource.addLast48H(last48H)
                splashScreenCallbackInterface.loadGetEntryCompleted()
            }

            override fun returnConnectionError()  {
                splashScreenCallbackInterface.returnConnectionError()
            }

            override fun returnTimeOutError() {
                splashScreenCallbackInterface.returnTimeOutError()
            }

            override fun getCountiesReturn() {
                splashScreenCallbackInterface.loadGetCountiesCompleted()
            }


        }
    }

    private fun cleanDataFromMap(key: MutableCollection<String?>): String? {

        for(value in key){
            if (value==null){
                return "0"
            }
        }

        return key.toString().replace("[", "").replace("]", "").replace(".0", "").trim()
    }
}