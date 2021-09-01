package pt.ulusofona.deisi.a2020.cm.g25.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.MainInterface
import pt.ulusofona.deisi.a2020.cm.g25.model.logic.MainLogic

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