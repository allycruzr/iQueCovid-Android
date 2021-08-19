package pt.ulusofona.deisi.a2020.cm.g25.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g25.data.local.room.AppDatabase
import pt.ulusofona.deisi.a2020.cm.g25.domain.interfaces.CountiesInterface
import pt.ulusofona.deisi.a2020.cm.g25.domain.logic.CountiesLogic


class CountiesViewModel(application: Application): AndroidViewModel(application) {
    private var listener: CountiesInterface? = null
    private var countiesLogic: CountiesLogic? = null

    private val storage = AppDatabase.getInstance(application).appDao()


    fun registerListener(listener: CountiesInterface) {
        this.listener = listener
        countiesLogic = CountiesLogic(storage, listener)
    }

    fun searchCounties(name: String? = null) {
        if(name.isNullOrEmpty()) {
            countiesLogic?.getAllCounties()
        } else {
            countiesLogic?.getCountiesByName(name)
        }
    }
}