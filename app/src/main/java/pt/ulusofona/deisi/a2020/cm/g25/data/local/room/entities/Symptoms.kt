package pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "symptoms")
class Symptoms {
    @PrimaryKey
    var uuid:String =""

    @SerializedName("data")
    var date: String? = null

    @SerializedName("data_dados")
    var dateOfData: String? = null

    @SerializedName("sintomas_tosse")
    var cough: Float? = null

    @SerializedName("sintomas_febre")
    var fever: Float? = null

    @SerializedName("sintomas_dificuldade_respiratoria")
    var shortBreath: Float? = null

    @SerializedName("sintomas_cefaleia")
    var headAche : Float? = null

    @SerializedName("sintomas_dores_musculares")
    var muscleAches : Float? = null

    @SerializedName("sintomas_fraqueza_generalizada")
    var generalWeakness : Float? = null



}