package pt.ulusofona.deisi.a2020.cm.g25.api

import pt.ulusofona.deisi.a2020.cm.g25.util.Constants.Companion.COUNTYS
import retrofit2.http.GET

interface SimpleApi {

    @GET(COUNTYS)
    suspend fun getCounty(): MutableList<String>


}