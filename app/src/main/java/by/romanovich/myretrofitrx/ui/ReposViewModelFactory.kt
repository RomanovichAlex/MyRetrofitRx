package by.romanovich.myretrofitrx.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.romanovich.myretrofitrx.domain.ProjectsRepo

//скачали из гуугла
    //передаем сюда репозиторий
class ReposViewModelFactory(private val repo: ProjectsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReposViewModel(repo) as T
    }
}