package by.romanovich.myretrofitrx.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.romanovich.myretrofitrx.domain.GitProjectEntity
import by.romanovich.myretrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ReposViewModel(private val gitProjectRepo: ProjectsRepo) : ViewModel() {
    //что бы закидывать объекты
    private val _repos = MutableLiveData<List<GitProjectEntity>>()
    //что бы читать объекты
    val repos: LiveData<List<GitProjectEntity>> = _repos

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> =_inProgress

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    //отметка вьюмодели
    fun onShowRepos(username: String) {
        _inProgress.postValue(true)
        compositeDisposable.add(
            gitProjectRepo
                .observeReposForUser(username)
                    //подписываемся
                .subscribeBy {
                    _inProgress.postValue(false)
                    //берем лайв дату и передаем список
                    _repos.postValue(it)
                    //_error
                }
        )
    }

    //метод отписки от всех
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}