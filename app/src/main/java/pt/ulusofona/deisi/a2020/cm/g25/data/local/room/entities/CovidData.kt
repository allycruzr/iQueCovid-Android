package pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coviddata")
class CovidData() {

    @PrimaryKey
    var uuid:String =""

    @SerializedName("data")
    var date: String? = null

    @SerializedName("data_dados")
    var dateOfData: String? = null

    @SerializedName("confirmados")
    var confirmed = 0

    @SerializedName("confirmados_novos")
    var newlyConfirmed = 0

    @SerializedName("recuperados")
    var recovered = 0

    @SerializedName("obitos")
    var deaths: Int? = 0

    /** Confirmados por Zonas **/

    @SerializedName("confirmados_arsnorte")
    var confirmedNorth: Int = 0

    @SerializedName("confirmados_arslvt")
    var confirmedLvt: Int = 0

    @SerializedName("confirmados_arscentro")
    var confirmedCenter: Int = 0

    @SerializedName("confirmados_arsalentejo")
    var confirmedAlentejo: Int = 0

    @SerializedName("confirmados_arsalgarve")
    var confirmedAlgarve: Int = 0

    @SerializedName("confirmados_acores")
    var confirmedAzores: Int = 0

    @SerializedName("confirmados_madeira")
    var confirmedMadeira: Int = 0

    /** Óbitos por Zonas **/

    @SerializedName("obitos_arsnorte")
    var deathsNorth: Int = 0

    @SerializedName("obitos_arslvt")
    var deathsLvt: Int = 0

    @SerializedName("obitos_arscentro")
    var deathsCenter: Int = 0

    @SerializedName("obitos_arsalentejo")
    var deathsAlentejo: Int = 0

    @SerializedName("obitos_arsalgarve")
    var deathsAlgarve: Int = 0

    @SerializedName("obitos_acores")
    var deathsAzores: Int = 0

    @SerializedName("obitos_madeira")
    var deathsMadeira: Int = 0

    /** Recuperados por Zonas **/

    @SerializedName("recuperados_arsnorte")
    var recoveredNorth: Int = 0

    @SerializedName("recuperados_arscentro")
    var recoveredCenter: Int = 0

    @SerializedName("recuperados_arslvt")
    var recoveredLvt: Int = 0

    @SerializedName("recuperados_arsalentejo")
    var recoveredAlentejo: Int = 0

    @SerializedName("recuperados_arsalgarve")
    var recoveredAlgarve: Int = 0

    @SerializedName("recuperados_acores")
    var recoveredAzores: Int = 0

    @SerializedName("recuperados_madeira")
    var recoveredMadeira: Int = 0
}