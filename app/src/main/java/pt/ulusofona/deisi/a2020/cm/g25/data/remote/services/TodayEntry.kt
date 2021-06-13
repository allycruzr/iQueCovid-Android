package pt.ulusofona.deisi.a2020.cm.g25.data.remote.services

import com.google.android.gms.common.api.Response
import com.google.gson.internal.LinkedTreeMap
import retrofit2.http.GET
import retrofit2.http.Path

interface TodayEntry {

    //@GET("/Requests/get_last_update")
    //suspend fun getLatest(): Response<EntryResponse>

    @GET("/Requests/get_entry/{date}")
    suspend fun getEntry(@Path(value="date") date:String): LinkedTreeMap<String, LinkedTreeMap<Any, Any>>

//    @GET("/Requests/get_last_update_specific_county/{county}")
//    suspend fun getCounty(@Path(value="county") county: String): Array<RiskResponse>
}