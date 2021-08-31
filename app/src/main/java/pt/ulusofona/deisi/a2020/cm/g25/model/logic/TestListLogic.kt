package pt.ulusofona.deisi.a2020.cm.g25.model.logic

import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.TestResult
import pt.ulusofona.deisi.a2020.cm.g25.model.remote.RemoteData
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.TestListLogicCallbackInterface
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.TestListLogicInterface

class TestListLogic(val remoteData: RemoteData) {

    private lateinit var testListLogicCallbackInterface: TestListLogicCallbackInterface

    fun getTestsList(testListLogicInterface: TestListLogicInterface) {
        createInterface(testListLogicInterface)
        remoteData.getTestListDB(testListLogicCallbackInterface)
    }

    private fun createInterface(testsLogicInterface: TestListLogicInterface) {
        testListLogicCallbackInterface = object : TestListLogicCallbackInterface {
            override fun testsListReturn(response: List<TestResult>) {
                testsLogicInterface.testsListReturn(response)
            }

        }
    }
}
