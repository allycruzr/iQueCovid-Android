package pt.ulusofona.deisi.a2020.cm.g25.model.local.datasource

import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.Symptoms
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.TestResult
import kotlin.math.roundToInt

class DataSource {

    private var _dataLast24hours = CovidData()
    private var _dataLast48hours = CovidData()
    private var _symptoms = Symptoms()

    private var counties = ArrayList<County>()
    private var tests = ArrayList<TestResult>()

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

    fun maleCasesToday(): Int {
        return dataLast24hours.confirmedMale!! - dataLast48hours.confirmedMale!!
    }

    fun femaleCasesToday(): Int {
        return dataLast24hours.confirmedFemale!! - dataLast48hours.confirmedFemale!!
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

    /** Cases MF **/

    fun casesMale(): Int {
        return dataLast24hours.recoveredNorth!! - dataLast48hours.recoveredNorth!!
    }

    fun casesFemale(): Int {
        return dataLast24hours.recoveredLvt!! - dataLast48hours.recoveredLvt!!
    }

    /** End of Table **/

    fun addCounties(response: ArrayList<County>) {
        counties = response
    }
    fun getAllCounties(): ArrayList<County> {
        return counties
    }

    fun addTests(response: ArrayList<TestResult>) {
        tests = response
    }
    fun getAllTests(): ArrayList<TestResult>? {
        return tests
    }

    fun getCoughPercentage () : Int {
        if (symptoms.cough != null){
            return symptoms.cough!!.times(100).roundToInt()
        }
        return 0
    }

    fun getFeverPercentage () : Int {
        if (symptoms.fever != null){
            return symptoms.fever!!.times(100).roundToInt()
        }
        return 0
    }

    fun getShortBreathPercentage () : Int {
        if (symptoms.shortBreath != null){
            return symptoms.shortBreath!!.roundToInt()
        }
        return 0
    }

    fun getHeadAchePercentage () : Int {
        if (symptoms.headAche != null){
            return symptoms.headAche!!.times(100).roundToInt()
        }
        return 0
    }

    fun getMuscleAchesPercentage () : Int {
        if (symptoms.muscleAches != null){
            return symptoms.muscleAches!!.times(100).roundToInt()
        }
        return 0
    }

    fun getGeneralWeaknessPercentage () : Int {
        if (symptoms.generalWeakness != null){
            return symptoms.generalWeakness!!.times(100).roundToInt()
        }
        return 0
    }

}