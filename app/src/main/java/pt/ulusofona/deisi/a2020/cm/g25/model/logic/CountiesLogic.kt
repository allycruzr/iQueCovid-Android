package pt.ulusofona.deisi.a2020.cm.g25.model.logic

import kotlinx.coroutines.*
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.dao.AppDao
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.CountiesInterface


class CountiesLogic(private val storage: AppDao, private val listener: CountiesInterface?) {
    // private val dataSource = DataSource.getInstance()

    fun getAllCounties() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ArrayList(storage.getAllCounties())
            launch(Dispatchers.Main) {
                listener?.onCountySearched(result)
            }
        }
    }

    fun filterCounties(
        name: String?,
        risk: String?,
        min: Int?,
        max: Int?,
        minArea: Float?,
        maxArea: Float?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = ArrayList(storage.searchCounties(
                "${name?:""}%",
                min?:-1,
                max?:Int.MAX_VALUE,
                risk?:"%",
                minArea?:-1f,
                maxArea?:Float.MAX_VALUE,
            ))
            launch(Dispatchers.Main) {
                listener?.onCountySearched(result)
            }
        }
    }
}