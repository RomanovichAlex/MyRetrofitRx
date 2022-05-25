package by.romanovich.myretrofitrx.di

import by.romanovich.myretrofitrx.data.RetrofitProjectsRepoImpl
import by.romanovich.myretrofitrx.data.retrofit.GitHubApi
import by.romanovich.myretrofitrx.domain.ProjectsRepo
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//val apiUrl = "https://api.github.com/"

val appModule = module {


    // single instance of HelloRepository
    single<String>(named("api_url")){"https://api.github.com/"}
    single<String>(named("default_user")){"borhammere"}
    single<ProjectsRepo> { RetrofitProjectsRepoImpl(get()) }
    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java)}
    single/*<Retrofit>*/{
        Retrofit.Builder()
        .baseUrl(get<String>(named("api_url")))
        //упрощаем
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        //.addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(get())
        .build()}
    //каждый раз когда запрашивают мы возращаем новый конвертер
    factory<Converter.Factory> { GsonConverterFactory.create()}
    /*// Single presenter factory
    factory { MyViewModel(get()) }*/
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