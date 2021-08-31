package pt.ulusofona.deisi.a2020.cm.g25.model.interfaces

import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.TestResult

interface TestListLogicInterface {
    fun testsListReturn(response: List<TestResult>)
}
