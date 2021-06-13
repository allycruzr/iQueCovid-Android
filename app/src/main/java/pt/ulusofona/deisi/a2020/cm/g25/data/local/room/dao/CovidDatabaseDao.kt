package pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidData

@Dao
interface CovidDatabaseDao {
    @Insert
    suspend fun insert(data: CovidData)

    @Update
    suspend fun update(data: CovidData)

    @Query("SELECT * FROM coviddata WHERE uuid = :uuid")
    suspend fun getLatest(uuid: String) : CovidData
}