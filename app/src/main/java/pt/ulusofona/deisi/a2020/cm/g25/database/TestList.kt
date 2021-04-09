package pt.ulusofona.deisi.a2020.cm.g25.database

import android.util.Log
import pt.ulusofona.deisi.a2020.cm.g25.classes.Test

object TestList {
    val testList: ArrayList<Test> = ArrayList<Test>()
        get() = field                                                                                 // getter

    init {
        val test1: Test = Test("01-01-2021", "Hospital Santa Maria", "Positivo", "N/A")
        val test2: Test = Test("11-02-2021", "Hospital São José", "Negativo", "teste_covid.png")
        val test3: Test =
            Test("03-03-2021", "Hospital Beatriz Ângelo", "A Aguardar Resultado", "N/A")

        testList.add(test1)
        testList.add(test2)
        testList.add(test3)

        Log.i("TestList", "Lista inicial criada")
    }

    fun addTest(test: Test) {
        this.testList.add(test)
        Log.i("TestList", "Adicionado um novo teste à lista")
    }

    fun removeTest(test: Test) {
        this.testList.remove(test)
        Log.i("TestList", "Removido um novo teste à lista")
    }

    fun getLength(): Int {
        return this.testList.size
    }


}