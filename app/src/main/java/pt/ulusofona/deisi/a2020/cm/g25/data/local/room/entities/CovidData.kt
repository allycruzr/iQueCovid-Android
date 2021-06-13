package pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class CovidData(
    var data: String, var data_dados: String, var confirmados: Int, var confirmados_norte: Int,
    var confirmados_centro: Int, var confirmados_lvt: Int, var confirmados_alentejo: Int,
    var confirmados_algarve: Int, var confirmados_scores: Int, var confirmados_madeira: Int,
    var confirmados_novos: Int, var recuperados: Int, var obitos: Int, var sintomas_tosse: Float?,
    var sintomas_febre: Float?, var sintomas_dificuldade_respiratoria: Float?,
    var sintomas_cefaleia: Float?, var sintomas_dores_musculares: Float?,
    var sintomas_fraqueza_generalizada: Float?, var obitos_norte: Int, var obitos_centro: Int,
    var obitos_lvt: Int, var obitos_alentejo: Int, var obitos_algarve: Int, var obitos_acores: Int,
    var obitos_madeira: Int, var recuperados_norte: Int?, var recuperados_centro: Int?,
    var recuperados_lvt: Int?, var recuperados_alentejo: Int?, var recuperados_algarve: Int?,
    var recuperados_acores: Int?, var recuperados_madeira: Int?, var recuperados_estrangeiro: Int?,
    var ativos: Int?
    ) {

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()
}