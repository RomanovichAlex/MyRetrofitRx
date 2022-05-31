package by.romanovich.myretrofitrx.data

import by.romanovich.myretrofitrx.domain.GitProjectEntity
import by.romanovich.myretrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single

//DATA - это реализация, которая зависит от сторонних библиотек(retrofit, роом...)

class MockProjectsRepoImpl : ProjectsRepo {

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
        val dummyList = listOf(
            GitProjectEntity(0, "!!!"),
            GitProjectEntity(1, "Ololo"),
            GitProjectEntity(2, "Fljenfljwnfe"),
            GitProjectEntity(3, "wevlkwnev"),
            GitProjectEntity(4, "otmknm"),
            GitProjectEntity(5, "dflkbm;slmfbv"),
        )

        return Single.just(dummyList)
    }
}
