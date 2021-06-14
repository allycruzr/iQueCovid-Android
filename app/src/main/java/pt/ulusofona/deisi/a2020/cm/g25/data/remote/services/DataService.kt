package pt.ulusofona.deisi.a2020.cm.g25.data.remote.services

import com.google.gson.internal.LinkedTreeMap
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.responses.CountyRiskResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {
    @GET("/Requests/get_entry/{date_1}")
    suspend fun getEntry(@Path(value="date1") date1:String): LinkedTreeMap<String, LinkedTreeMap<String, Any?>>

    @GET("/Requests/get_entry/{date_1}_until_{date_2}")
    suspend fun getEntry(@Path(value="date1") date1:String, @Path(value="date2") date2:String): LinkedTreeMap<String, LinkedTreeMap<String,Any?>>

    @GET("/Requests/get_last_update_specific_county/{county}")
    suspend fun getLastUpdateCounty(@Path(value="county") county:String): Array<CountyRiskResponse>
}