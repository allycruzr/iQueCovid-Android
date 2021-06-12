package pt.ulusofona.deisi.a2020.cm.g25.repository

import pt.ulusofona.deisi.a2020.cm.g25.api.RetrofitInstance

class Repository {

    suspend fun getCounties(): MutableList<String> {
        return RetrofitInstance.api.getCounty()
    }

}