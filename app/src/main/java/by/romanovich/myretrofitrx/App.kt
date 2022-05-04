package by.romanovich.myretrofitrx

import android.app.Application
import android.content.Context
import by.romanovich.myretrofitrx.data.RetrofitProjectsRepoImpl

import by.romanovich.myretrofitrx.domain.ProjectsRepo

class App: Application() {
    val gitProjectsRepo: ProjectsRepo by lazy { RetrofitProjectsRepoImpl() }

}

val Context.app: App
    get() = applicationContext as App