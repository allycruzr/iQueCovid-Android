package pt.ulusofona.deisi.a2020.cm.g25.ui.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g25.repository.Repository

class MainViewModel(private val repository: Repository): ViewModel() {

    lateinit var myResponse: String

    fun getCounties() {
        viewModelScope.launch {
            val response:String = repository.getCounties().toString()
            myResponse = response

        }
    }
}