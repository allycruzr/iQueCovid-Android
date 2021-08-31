package pt.ulusofona.deisi.a2020.cm.g25.view.fragments.testlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.AppDatabase
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.TestResult
import pt.ulusofona.deisi.a2020.cm.g25.model.remote.RemoteData
import pt.ulusofona.deisi.a2020.cm.g25.model.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.TestListInterface
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.TestListLogicInterface
import pt.ulusofona.deisi.a2020.cm.g25.model.logic.TestListLogic
import pt.ulusofona.deisi.a2020.cm.g25.view.utils.Constants.Companion.BASE_URL

class TestListViewModel(application: Application) : AndroidViewModel(application) {
    private val storage = AppDatabase.getInstance(application).appDao()
    private var listener: TestListInterface? = null
    private lateinit var testListLogicInterface: TestListLogicInterface

    val remoteData = RemoteData(
        storage,
        RetrofitBuilder.getInstance(BASE_URL),
        application
    )

    private val testsLogic = TestListLogic(remoteData)

    fun registerListener(testListInterface: TestListInterface) {
        listener = testListInterface
        testListLogicInterface = object : TestListLogicInterface {
            override fun testsListReturn(response: List<TestResult>) {
                CoroutineScope(Dispatchers.Main).launch {
                    listener?.getTestsListCompleted(response as ArrayList<TestResult>)
                }
            }
        }
    }

    fun getTestsList() {
        testsLogic.getTestsList(testListLogicInterface)
    }
}