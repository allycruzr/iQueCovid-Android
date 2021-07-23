package pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource

import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.Symptoms

class DataSource {

    private var _dataLast24hours = CovidData()
    private var _dataLast48hours = CovidData()
    private var _symptoms = Symptoms()

    private var counties = ArrayList<County>()

    val dataLast24hours
    get() = _dataLast24hours

    val dataLast48hours
    get() = _dataLast48hours

    val symptoms
    get() = _symptoms

    companion object {
        private var instance: DataSource? = null

        fun getInstance(): DataSource {
            synchronized(this) {
                if (instance == null) {
                    instance = DataSource()
                }
                return instance as DataSource
            }
        }
    }

    fun addLast24H(data: CovidData) {
        _dataLast24hours = data
    }
    fun addLast48H(data: CovidData) {
        _dataLast48hours = data
    }

    fun addSymptoms(symptoms: Symptoms) {
        _symptoms = symptoms
    }

    fun dateOfData(): String {
        return _dataLast24hours.date.toString()
    }

    /** Dashboard Info **/

    /** Header **/

    fun confirmedToday(): Int {
        return _dataLast24hours.confirmed!! - dataLast48hours.confirmed!!
    }

    fun recoveredToday(): Int {
        return dataLast24hours.recovered!! - dataLast48hours.recovered!!
    }

    fun deathsToday(): Int {
        return dataLast24hours.deaths!! - dataLast48hours.deaths!!
    }

    /** Table **/
    /** Confirmed **/

    fun confirmedNorthToday(): Int {
        return dataLast24hours.confirmedNorth!! - dataLast48hours.confirmedNorth!!
    }

    fun confirmedLVTToday(): Int {
        return dataLast24hours.confirmedLvt!! - dataLast48hours.confirmedLvt!!
    }

    fun confirmedCenterToday(): Int {
        return dataLast24hours.confirmedCenter!! - dataLast48hours.confirmedCenter!!
    }

    fun confirmedAlentejoToday(): Int {
        return dataLast24hours.confirmedAlentejo!! - dataLast48hours.confirmedAlentejo!!
    }

    fun confirmedAlgarveToday(): Int {
        return dataLast24hours.confirmedAlgarve!! - dataLast48hours.confirmedAlgarve!!
    }

    fun confirmedAzoresToday(): Int {
        return dataLast24hours.confirmedAzores!! - dataLast48hours.confirmedAzores!!
    }

    fun confirmedMadeiraToday(): Int {
        return dataLast24hours.confirmedMadeira!! - dataLast48hours.confirmedMadeira!!
    }

    /** Recovered **/

    fun recoveredNorthToday(): Int {
        return dataLast24hours.recoveredNorth!! - dataLast48hours.recoveredNorth!!
    }

    fun recoveredLVTToday(): Int {
        return dataLast24hours.recoveredLvt!! - dataLast48hours.recoveredLvt!!
    }

    fun recoveredCenterToday(): Int {
        return dataLast24hours.recoveredCenter!! - dataLast48hours.recoveredCenter!!
    }

    fun recoveredAlentejoToday(): Int {
        return dataLast24hours.recoveredAlentejo!! - dataLast48hours.recoveredAlentejo!!
    }

    fun recoveredAlgarveToday(): Int {
        return dataLast24hours.recoveredAlgarve!! - dataLast48hours.recoveredAlgarve!!
    }

    fun recoveredAzoresToday(): Int {
        return dataLast24hours.recoveredAzores!! - dataLast48hours.recoveredAzores!!
    }

    fun recoveredMadeiraToday(): Int {
        return dataLast24hours.recoveredMadeira!! - dataLast48hours.recoveredMadeira!!
    }

    /** Deaths **/

    fun deathsNorthToday(): Int {
        return dataLast24hours.deathsNorth!! - dataLast48hours.deathsNorth!!
    }

    fun deathsLVTToday(): Int {
        return dataLast24hours.deathsLvt!! - dataLast48hours.deathsLvt!!
    }

    fun deathsCenterToday(): Int {
        return dataLast24hours.deathsCenter!! - dataLast48hours.deathsCenter!!
    }

    fun deathsAlentejoToday(): Int {
        return dataLast24hours.deathsAlentejo!! - dataLast48hours.deathsAlentejo!!
    }

    fun deathsAlgarveToday(): Int {
        return dataLast24hours.deathsAlgarve!! - dataLast48hours.deathsAlgarve!!
    }

    fun deathsAzoresToday(): Int {
        return dataLast24hours.deathsAzores!! - dataLast48hours.deathsAzores!!
    }

    fun deathsMadeiraToday(): Int {
        return dataLast24hours.deathsAzores!! - dataLast48hours.deathsAzores!!
    }

    /** End of Table **/

    fun addCounties(response: ArrayList<County>) {
        counties = response
    }
    fun getAllCounties(): ArrayList<County> {
        return counties
    }
}