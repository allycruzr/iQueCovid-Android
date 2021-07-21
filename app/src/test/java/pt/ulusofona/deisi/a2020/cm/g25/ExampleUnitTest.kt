package pt.ulusofona.deisi.a2020.cm.g25

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.RetrofitBuilder
import pt.ulusofona.deisi.a2020.cm.g25.data.remote.services.DataService
import pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels.ENDPOINT
import retrofit2.Retrofit
import java.net.ConnectException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

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