package pt.ulusofona.deisi.a2020.cm.g25.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g25.model.local.room.AppDatabase
import pt.ulusofona.deisi.a2020.cm.g25.model.interfaces.CountiesInterface
import pt.ulusofona.deisi.a2020.cm.g25.model.logic.CountiesLogic


class CountiesViewModel(application: Application) : AndroidViewModel(application) {
    private var listener: CountiesInterface? = null
    private var countiesLogic: CountiesLogic? = null

    private val storage = AppDatabase.getInstance(application).appDao()


    fun registerListener(listener: CountiesInterface) {
        this.listener = listener
        countiesLogic = CountiesLogic(storage, listener)
    }

    fun searchCounties(
        name: String? = null,
        risk: String? = null,
        min: Int? = null,
        max: Int? = null
    ) {
        val hasFilters = listOf<Any?>(name, risk, min, max).any { it != null }
        if (hasFilters) {
            countiesLogic?.filterCounties(name, risk, min, max)
        } else {
            countiesLogic?.getAllCounties()
        }
    }
}