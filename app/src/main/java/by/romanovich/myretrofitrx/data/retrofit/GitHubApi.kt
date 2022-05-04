package by.romanovich.myretrofitrx.data.retrofit

import by.romanovich.myretrofitrx.domain.GitProjectEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<GitProjectEntity>>
}