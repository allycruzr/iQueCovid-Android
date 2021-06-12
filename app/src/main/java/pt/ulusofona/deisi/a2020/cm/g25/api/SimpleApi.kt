package pt.ulusofona.deisi.a2020.cm.g25.api

import retrofit2.http.GET

interface SimpleApi {

    @GET("Requests/get_county_list/")
    suspend fun getCounty(): MutableList<String>


}