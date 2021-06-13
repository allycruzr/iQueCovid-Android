package pt.ulusofona.deisi.a2020.cm.g25.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pt.ulusofona.deisi.a2020.cm.g25.repository.Repository
import pt.ulusofona.deisi.a2020.cm.g25.ui.views.MainViewModel

class ModelViewFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}