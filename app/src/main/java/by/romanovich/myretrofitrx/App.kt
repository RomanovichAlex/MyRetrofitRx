package by.romanovich.myretrofitrx

import android.app.Application
import android.content.Context
import by.romanovich.myretrofitrx.data.RetrofitProjectsRepoImpl
import by.romanovich.myretrofitrx.data.retrofit.GitHubApi
import by.romanovich.myretrofitrx.di.appModule

import by.romanovich.myretrofitrx.domain.ProjectsRepo
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    /*private val apiUrl = "https://api.github.com/"
    private val gsonConverter by lazy{ GsonConverterFactory.create()}
    private val retrofit by lazy{ Retrofit.Builder()
        .baseUrl(apiUrl)
        //упрощаем
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        //.addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(gsonConverter)
        .build()
    }
    private val gitHubApi: GitHubApi = retrofit.create(GitHubApi::class.java)
   val gitProjectsRepo: ProjectsRepo by lazy {
       RetrofitProjectsRepoImpl(gitHubApi) }
    //val gitProjectsRepo: ProjectsRepo by lazy { RStringProjectsRepoImpl(this) }*/

    //koin
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }

}






val Context.app: App
    get() = applicationContext as App