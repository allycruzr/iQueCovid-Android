package pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.ProjectDatabase
import pt.ulusofona.deisi.a2020.cm.g25.database.IQueCovidLogic

const val ENDPOINT = "https://covid19-api.vost.pt/"

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val storage = ProjectDatabase.getInstance(application).covidDatabaseDao()
    private val iQueCovidLogic = IQueCovidLogic(storage)



}