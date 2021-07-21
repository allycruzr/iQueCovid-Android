package pt.ulusofona.deisi.a2020.cm.g25.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao.AppDao
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.CovidData

@Database (entities = arrayOf(CovidData::class, County::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(applicationContext: Context): AppDatabase {
            synchronized(this) {
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "app_db").build()
                }
                return instance as AppDatabase
            }
        }
    }
}