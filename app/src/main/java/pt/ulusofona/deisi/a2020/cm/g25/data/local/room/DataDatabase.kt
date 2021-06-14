package pt.ulusofona.deisi.a2020.cm.g25.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao.CovidDatabaseDao
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidDataDB

@Database (entities = arrayOf(CovidDataDB::class), version = 1)
abstract class DataDatabase: RoomDatabase() {
    abstract fun covidDatabaseDao(): CovidDatabaseDao

    companion object {
        private var instance: DataDatabase? = null

        fun getInstance(applicationContext: Context): DataDatabase {
            synchronized(this) {
                if(instance == null) {
                    instance = Room.databaseBuilder(applicationContext, DataDatabase::class.java,
                        "covid_data_db").build()
                }
                return instance as DataDatabase
            }
        }
    }
}