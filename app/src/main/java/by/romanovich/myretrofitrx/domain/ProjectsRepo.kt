package by.romanovich.myretrofitrx.domain
import io.reactivex.rxjava3.core.Single

//domain - предметная область которая не от чего не зависит, чистая логика
interface ProjectsRepo {
    // C(R)UD получение списка проектов по юзеру
    fun observeReposForUser(username: String): Single<List<GitProjectEntity>>
}