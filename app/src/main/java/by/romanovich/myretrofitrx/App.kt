package by.romanovich.myretrofitrx

import android.app.Application
import android.content.Context

import by.romanovich.myretrofitrx.di.AppDependenciesComponent
import by.romanovich.myretrofitrx.di.AppDependenciesModule
import by.romanovich.myretrofitrx.di.DaggerAppDependenciesComponent


class App : Application() {
    lateinit var appDependenciesComponent: AppDependenciesComponent
    override fun onCreate() {
        super.onCreate()
        appDependenciesComponent = DaggerAppDependenciesComponent
            .builder()
                //сами ложим нужный модуль
            .appDependenciesModule(AppDependenciesModule(this))
            .build()
    }
}

//
val Context.app: App
    get() {
        return applicationContext as App
    }
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











    /*//koin
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }*/
