package pt.ulusofona.deisi.a2020.cm.g25.domain.logic

import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County

class MainLogic {
    val dataSource = DataSource.getInstance()

    fun checkDangerZone(district: String): String {
        val counties = dataSource.getAllCounties()

        var countiesList: List<County> = counties.filter { s ->
            s.district.toLowerCase().trim() == district.toLowerCase().trim()
        }

        for (county in countiesList) {
            if( county.risk.equals("Moderado")){
                return "Moderado"
            }
            if( county.risk.equals("Elevado")){
                return "Elevado"
            }
            if( county.risk.equals("Muito Elevado") || county.risk.equals("Extremamente Elevado")){
                return "Muito Elevado"
            }
        }
        return "Baixo"
    }
}