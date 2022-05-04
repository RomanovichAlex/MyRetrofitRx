package by.romanovich.myretrofitrx.domain

import io.reactivex.rxjava3.core.Single

interface ProjectsRepo {
    // C(R)UD получение списка проектов по юзеру
    fun observeReposForUser(username: String): Single<List<GitProjectEntity>>
}