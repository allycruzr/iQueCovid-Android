package pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.CountiesInterface
import pt.ulusofona.deisi.a2020.cm.g25.domain.logic.CountiesLogic
import pt.ulusofona.deisi.a2020.cm.g25.domain.logic.MainLogic


class CountiesViewModel(application: Application): AndroidViewModel(application) {
    private var listener: CountiesInterface? = null

    private val countiesLogic = CountiesLogic()

    fun registerListener(listener: CountiesInterface) {
        this.listener = listener
    }

    fun getAllCounties(){
        listener?.initCounties(countiesLogic.getAllCounties())
    }
}