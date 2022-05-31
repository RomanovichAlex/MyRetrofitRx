package by.romanovich.myretrofitrx.di

import android.content.Context
import by.romanovich.myretrofitrx.data.RetrofitProjectsRepoImpl
import by.romanovich.myretrofitrx.data.retrofit.GitHubApi
import by.romanovich.myretrofitrx.domain.ProjectsRepo
import by.romanovich.myretrofitrx.ui.ReposViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

//di- задача склейки domain и data

//val apiUrl = "https://api.github.com/"

/* KOIN
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

    viewModel { ReposViewModel(get()) }
}*/


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


//описание того как создавать объекты
@Module
class AppDependenciesModule(val context: Context) {

    @Singleton
    @Provides
    //получаем GitHubApi
    fun provideGitHubApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

    //делаем сам репозиторий
    @Singleton
    @Provides
    fun provideProjectRepo(api: GitHubApi): ProjectsRepo {
        return RetrofitProjectsRepoImpl(api)
    }


    //возращает kshalnov
    @Provides
    @Named("username")
    fun provideDefaultUserName(): String {
        return "kshalnov"
    }

    @Provides
    //создаем зависимость BaseUrl,  метод который представляет зависимость т.е. Provides
    @Named("api_url")
    fun provideBaseUrl(): String {
        return "https://api.github.com/"
    }

    //создаем зависимость Converter.Factory,  метод который представляет зависимость т.е. Provides
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    //метод который представляет как в коин single<String>(named("api_url")){"https://api.github.com/"}
    @Singleton
    //метод который представляет зависимость т.е. Provides
    @Provides
    //метод который возращает retrofit, на вход нужен baseUrl, converterFactory
    fun provideRetrofit(@Named("api_url") baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
    }
}