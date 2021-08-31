package pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "testresult")
class TestResult(val date: String, val local: String, val resultado: String, val ficheiro: ByteArray?) {
    @PrimaryKey
    var uuid:String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "Teste(date='$date', local='$local', resultado='$resultado', ficheiro='$ficheiro')"
    }
}