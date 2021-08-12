package pt.ulusofona.deisi.a2020.cm.g25.domain.logic

import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County


class CountiesLogic {
    private val dataSource = DataSource.getInstance()

    fun getAllCounties() = dataSource.getAllCounties()

    fun getCountiesByName(name: String): ArrayList<County> {
        return ArrayList(getAllCounties().filter { it.county.toLowerCase().startsWith(name.toLowerCase()) })
    }

}