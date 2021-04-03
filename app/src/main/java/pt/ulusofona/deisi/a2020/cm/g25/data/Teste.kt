package pt.ulusofona.deisi.a2020.cm.g25.data

class Teste(date:String, local: String, resultado: String, ficheiro: String) {
    var date: String = date
        get() = if(field == "") "N/A" else field        // getter (Se String vazia, devolve N/A)
        set(value) {                                    // setter
            field = value
        }
    var local: String = local
        get() = if(field == "") "N/A" else field        // getter (Se String vazia, devolve N/A)
        set(value) {                                    // setter
            field = value
        }
    var resultado: String = resultado
        get() = if(field == "") "N/A" else field        // getter (Se String vazia, devolve N/A)
        set(value) {                                    // setter
            field = value
        }
    var ficheiro: String = ficheiro
        get() = if(field == "") "N/A" else field        // getter (Se String vazia, devolve N/A)
        set(value) {                                    // setter
            field = value
        }

    override fun toString(): String {
        return "Teste(date='$date', local='$local', resultado='$resultado', ficheiro='$ficheiro')"
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}