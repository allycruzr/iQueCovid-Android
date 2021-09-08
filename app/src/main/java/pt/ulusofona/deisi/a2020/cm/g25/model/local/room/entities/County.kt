package pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "county")
class County {
    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

    @SerializedName("concelho")
    var county: String = ""

    @SerializedName("data")
    var dateOfData: String = ""

    @SerializedName("confirmados_14")
    var confirmedLast14Days: Int? = null

    @SerializedName("incidencia_risco")
    var risk: String = ""

    @SerializedName("incidencia")
    var rate: Int = 0

    @SerializedName("ars")
    var ars: String = ""

    @SerializedName("casos_14dias")
    var casesLast14Days: Int? = null

    @SerializedName("distrito")
    var district: String = ""

    @SerializedName("area")
    var area: Float? = null



}