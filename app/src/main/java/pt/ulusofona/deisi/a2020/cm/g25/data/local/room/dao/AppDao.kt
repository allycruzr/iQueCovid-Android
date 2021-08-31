package pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.Symptoms
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.TestResult

@Dao
interface AppDao {

    /** Dados Relacionados ao Covid -> CovidData **/

    @Insert
    suspend fun insertData(data: CovidData)

    @Query("SELECT * FROM coviddata")
    suspend fun getAllData(): List<CovidData>

    @Query("DELETE FROM coviddata")
    suspend fun deleteAllData()

    @Query("SELECT * FROM coviddata WHERE uuid = :uuid")
    suspend fun getById(uuid: String): CovidData

    /** Dados Relacionados aos Concelhos -> County **/

    @Insert
    suspend fun insertCounties(counties: ArrayList<County>)

    @Query("SELECT * FROM County")
    suspend fun getAllCounties():List<County>

    @Query("SELECT * FROM County WHERE county LIKE :name AND rate BETWEEN :min AND :max AND risk LIKE :risk")
    suspend fun searchCounties(name: String = "%", min: Int = -1, max: Int = Int.MAX_VALUE, risk: String ="%"): List<County>

    @Query("DELETE FROM County")
    suspend fun deleteAllCounties()

    /** Dados Relacionados aos Sintomas -> Symptoms **/

    @Insert
    suspend fun insertSymptoms(symptoms: Symptoms)

    @Query("SELECT * FROM symptoms")
    suspend fun getAllSymptoms(): List<Symptoms>

    @Query("DELETE FROM symptoms")
    suspend fun deleteAllSymptoms()

    @Query("SELECT * FROM symptoms WHERE uuid = :uuid")
    suspend fun getSymptomsById(uuid: String): Symptoms

    /** Dados Relacionados aos Testes -> TestRecord **/

    @Insert
    suspend fun insertTest(testResult: TestResult)

    @Query("SELECT * FROM testresult")
    suspend fun getAllTests():List<TestResult>

    @Query("SELECT * FROM testresult WHERE uuid = :uuid")
    suspend fun getTestById(uuid:String): TestResult


}