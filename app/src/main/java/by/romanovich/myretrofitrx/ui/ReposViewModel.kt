package by.romanovich.myretrofitrx.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.romanovich.myretrofitrx.domain.GitProjectEntity
import by.romanovich.myretrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
//ui - пользовательские интерфейсы связанные с этим подклассом, ui знает о domain, не знает о data.(Знает о бизнесс логике но не знает о реализации)
class ReposViewModel(
    private val gitProjectRepo: ProjectsRepo
    ) : ViewModel() {
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
                .subscribeBy (
                    onSuccess =
                        {
                    _inProgress.postValue(false)
                    //берем лайв дату и передаем список
                    _repos.postValue(it)
                    //_error
                },
                onError={

                        })
        )
    }

    //метод отписки от всех
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}