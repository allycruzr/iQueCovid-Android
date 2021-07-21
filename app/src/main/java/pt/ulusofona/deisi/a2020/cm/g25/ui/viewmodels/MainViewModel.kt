package pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.AppDatabase
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.MainInterface
import pt.ulusofona.deisi.a2020.cm.g25.domain.logic.MainLogic

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var listener: MainInterface? = null

    private val mainLogic = MainLogic()

    fun registerListener(listener: MainInterface) {
        this.listener = listener
    }

    fun checkDangerZone(adminArea:String){
        listener?.checkDangerZoneValue(mainLogic.checkDangerZone(adminArea))
    }
}