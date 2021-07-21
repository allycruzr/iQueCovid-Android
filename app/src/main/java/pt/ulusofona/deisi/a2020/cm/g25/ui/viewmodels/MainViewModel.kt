package pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.AppDatabase
import pt.ulusofona.deisi.a2020.cm.g25.domain.logic.MainLogic

const val ENDPOINT = "https://covid19-api.vost.pt/"

class MainViewModel(application: Application): AndroidViewModel(application) {


    private val storage = AppDatabase.getInstance(application).appDao()
    //private val repo = DataRepository(storage)
    private val logic: MainLogic = MainLogic()


}