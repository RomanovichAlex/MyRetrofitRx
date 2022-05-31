package by.romanovich.myretrofitrx.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.romanovich.myretrofitrx.domain.ProjectsRepo
//ui - пользовательские интерфейсы связанные с этим подклассом, ui знает о domain, не знает о data.(Знает о бизнесс логике но не знает о реализации)
//скачали из гуугла
    //передаем сюда репозиторий
class ReposViewModelFactory(private val repo: ProjectsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReposViewModel(repo) as T
    }
}