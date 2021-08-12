package pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces

import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.entities.County

interface CountiesInterface {
    fun initCounties(allCounties: ArrayList<County>)
}