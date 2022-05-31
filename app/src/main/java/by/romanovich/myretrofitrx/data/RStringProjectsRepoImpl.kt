package by.romanovich.myretrofitrx.data

import android.content.Context
import by.romanovich.myretrofitrx.R
import by.romanovich.myretrofitrx.domain.GitProjectEntity
import by.romanovich.myretrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single

//DATA - это реализация, которая зависит от сторонних библиотек(retrofit, роом...)

//для это реализации репозитория контекст это внешняя зависимость
class RStringProjectsRepoImpl(
    private val context: Context
) : ProjectsRepo {

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        val dummyList = listOf(
            GitProjectEntity(0, context.getString(R.string.username_0)),
            GitProjectEntity(1, context.getString(R.string.username_1)),
            GitProjectEntity(2, context.getString(R.string.username_2))
        )

        return Single.just(dummyList)
    }
}
