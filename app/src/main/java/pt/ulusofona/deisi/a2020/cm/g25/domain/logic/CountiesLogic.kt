package pt.ulusofona.deisi.a2020.cm.g25.domain.logic

import kotlinx.coroutines.*
import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.dao.AppDao
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.CountiesInterface


class CountiesLogic(private val storage: AppDao, private val listener: CountiesInterface?) {
    // private val dataSource = DataSource.getInstance()
    private val scope = CoroutineScope(Dispatchers.IO)

    fun getAllCounties() {
        scope.launch {
            val result = ArrayList(storage.getAllCounties())
            launch(Dispatchers.Main) {
                listener?.onCountySearched(result)
            }
        }
    }

    fun getCountiesByName(name: String){
        // return ArrayList(getAllCounties().filter { it.county.toLowerCase().startsWith(name.toLowerCase()) })
        scope.launch {
            val result = ArrayList(storage.searchCountiesByName("$name%"))
            launch(Dispatchers.Main) {
                listener?.onCountySearched(result)
            }
        }
    }
}