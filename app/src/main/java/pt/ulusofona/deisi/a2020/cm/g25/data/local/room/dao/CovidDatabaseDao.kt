package pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidDataDB

@Dao
interface CovidDatabaseDao {
    @Insert
    suspend fun insert(data: CovidDataDB)

    @Update
    suspend fun update(data: CovidDataDB)

    @Query("SELECT * FROM coviddatadb WHERE uuid = :uuid")
    suspend fun getLatest(uuid: String) : CovidDataDB
}