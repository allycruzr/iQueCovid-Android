package pt.ulusofona.deisi.a2020.cm.g25.model.remote.services

import com.google.gson.internal.LinkedTreeMap
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {
    @GET("/Requests/get_last_update")
    suspend fun getLastUpdate(): Response<CovidData>

    @GET("/Requests/get_entry/{date}")
    suspend fun getEntryFromDate(@Path(value="date") date1:String): LinkedTreeMap<String, LinkedTreeMap<String, String?>>

    @GET("/Requests/get_entry/{date}")
    suspend fun getEntrySymptomsFromDate(@Path(value="date") date1:String): LinkedTreeMap<String, LinkedTreeMap<String, String?>>

    @GET("/Requests/get_last_update_counties")
    suspend fun getLastUpdateCounties(): ArrayList<County>
}