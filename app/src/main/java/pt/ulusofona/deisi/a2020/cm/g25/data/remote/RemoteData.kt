package pt.ulusofona.deisi.a2020.cm.g25.data.remote

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao.AppDao
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.services.DataService
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.SplashScreenLogicCallbackInterface
import retrofit2.Retrofit
import java.net.ConnectException
import java.text.SimpleDateFormat
import java.util.*

class RemoteData(val storage: AppDao, val retrofit: Retrofit, val application: Application) {

    fun getLastUpdateFromDB(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        CoroutineScope(Dispatchers.IO).launch {
            val lastUpdate = storage.getById("24h")
            if (lastUpdate != null) {
                splashScreenLogicCallbackInterface.lastUpdateReturn(lastUpdate)
            } else {
                splashScreenLogicCallbackInterface.returnConnectionError()
            }
        }
    }

    fun getLastUpdateFromAPI(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        val service = retrofit.create(DataService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getLastUpdate()
                response.body()!!.uuid = "24h"
                storage.deleteAllData()
                storage.insertData(response.body()!!)
                splashScreenLogicCallbackInterface.lastUpdateReturn(response.body()!!)
            } catch (e: ConnectException) {
                splashScreenLogicCallbackInterface.returnTimeOutError()
            }
        }
    }

    fun getEntryFromDateWeb(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        val service = retrofit.create(DataService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val date: String = getPreviousDate()
                val response = service.getEntryFromDate(date)
                splashScreenLogicCallbackInterface.getEntryReturn(response, storage)
            } catch (e: ConnectException) {
                splashScreenLogicCallbackInterface.returnTimeOutError()
            }
        }

    }

    fun getEntryFromDateDB(splashScreenLogicReturnInterface: SplashScreenLogicCallbackInterface) {
        CoroutineScope(Dispatchers.IO).launch {
            val last48H = storage.getById("48h")
            splashScreenLogicReturnInterface.last48HReturnDB(last48H)
        }
    }

    fun getEntrySymptomsFromDateWeb(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        val service = retrofit.create(DataService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val date: String = "16-08-2020"
                val response = service.getEntrySymptomsFromDate(date)
                splashScreenLogicCallbackInterface.getEntrySymptomsReturn(response, storage)
            } catch (e: ConnectException) {
                splashScreenLogicCallbackInterface.returnTimeOutError()
            }
        }

    }

    fun getEntrySymptomsFromDateDB(splashScreenLogicReturnInterface: SplashScreenLogicCallbackInterface) {
        CoroutineScope(Dispatchers.IO).launch {
            val symptoms = storage.getSymptomsById("1")
            splashScreenLogicReturnInterface.symptomsReturnDB(symptoms)
        }
    }



    fun getCountiesWeb(splashScreenLogicCallbackInterface: SplashScreenLogicCallbackInterface) {
        val service = retrofit.create(DataService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getLastUpdateCounties()
                storage.deleteAllCounties()
                val dataSource = DataSource.getInstance()
                dataSource.addCounties(response)
                storage.insertCounties(response)
                splashScreenLogicCallbackInterface.getCountiesReturn()
            } catch (e: ConnectException) {
                splashScreenLogicCallbackInterface.returnTimeOutError()
            }
        }
    }

    fun getPreviousDate(): String {
        val dataSource = DataSource.getInstance()
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val dateLastUpdate: Date = sdf.parse(dataSource.dataLast24hours.date!!)

        val calendar = GregorianCalendar()
        calendar.time = dateLastUpdate
        calendar.add(Calendar.DATE, -1)
        val previousDate: String = sdf.format(calendar.time)
        return previousDate
    }
}