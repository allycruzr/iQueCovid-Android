package pt.ulusofona.deisi.a2020.cm.g25.domain.logic

import pt.ulusofona.deisi.a2020.cm.g25.data.local.datasource.DataSource


class CountiesLogic {
    val dataSource = DataSource.getInstance()

    fun getAllCounties() = dataSource.getAllCounties()

}