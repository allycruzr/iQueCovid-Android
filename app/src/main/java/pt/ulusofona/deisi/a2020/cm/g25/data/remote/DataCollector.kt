package pt.ulusofona.deisi.a2020.cm.g25.data.remote

import pt.ulusofona.deisi.a2020.cm.g25.data.remote.services.DataService
import pt.ulusofona.deisi.a2020.cm.g25.ui.utils.Constants.Companion.BASE_URL

class DataCollector {
    companion object{

        suspend fun GetDistrictoRisk(county: String): String{
            val service = RetrofitBuilder.getInstance(BASE_URL).create(DataService::class.java)
            val response = service.getLastUpdateCounty(county)
            val risco = response[0].incidencia_risco
            return risco
        }
    }
}