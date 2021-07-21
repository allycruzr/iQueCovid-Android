package pt.ulusofona.deisi.a2020.cm.g25

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.services.DataService
import pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels.ENDPOINT
import retrofit2.Retrofit
import java.net.ConnectException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("pt.ulusofona.deisi.a2020.cm.g25", appContext.packageName)
    }

    @Test
    fun getAPIData() {
        val retrofit: Retrofit = RetrofitBuilder.getInstance(ENDPOINT)
        val service = retrofit.create(DataService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getLastUpdate()
                response.body()!!.uuid = "24h"
                print("API Test: Body = ${response.body()}")
            } catch (e: ConnectException) {
                print("API Test: API REQUEST TIMEOUT!")
            }

        }

        //assertEquals(4, 2 + 2)

    }
}