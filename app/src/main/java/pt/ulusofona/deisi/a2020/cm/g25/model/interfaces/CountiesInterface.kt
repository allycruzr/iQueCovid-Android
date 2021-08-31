package pt.ulusofona.deisi.a2020.cm.g25.model.interfaces

import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.entities.County

interface CountiesInterface {
    fun onCountySearched(counties: ArrayList<County>)
}