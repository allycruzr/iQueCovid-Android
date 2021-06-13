package pt.ulusofona.deisi.a2020.cm.g25.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidData
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao.CovidDatabaseDao

@Database (entities = arrayOf(CovidData::class), version = 1)
abstract class ProjectDatabase: RoomDatabase() {
    abstract fun covidDatabaseDao(): CovidDatabaseDao

    companion object {
        private var instance: ProjectDatabase? = null

        fun getInstance(applicationContext: Context): ProjectDatabase {
            synchronized(this) {
                if(instance == null) {
                    instance = Room.databaseBuilder(applicationContext, ProjectDatabase::class.java,
                        "CovidDatabase").build()
                }
                return instance as ProjectDatabase
            }
        }
    }
}