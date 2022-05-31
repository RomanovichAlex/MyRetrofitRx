package by.romanovich.myretrofitrx.data

import by.romanovich.myretrofitrx.data.retrofit.GitHubApi
import by.romanovich.myretrofitrx.domain.GitProjectEntity
import by.romanovich.myretrofitrx.domain.ProjectsRepo
import io.reactivex.rxjava3.core.Single
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//DATA - это реализация, которая зависит от сторонних библиотек(retrofit, роом...)

//теперь репозиторий только может общаться с апи, превращает интерфейс репозитория в интерфейс гитХаб апишки
class RetrofitProjectsRepoImpl(
    private val api:GitHubApi
    //private val retrofit: Retrofit,
   // private val baseUrl:String,
    //private val gsonConverter: Converter.Factory
) : ProjectsRepo {

    /*private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
            //упрощаем
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        //.addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(gsonConverter)
        .build()*/
    //для создания api нужен ретрофит
    //private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

    override fun observeReposForUser(username: String): Single<List<GitProjectEntity>> {
    //упрощаем
    /*return Single.create { emitter ->
            api.listRepos(username)
                .enqueue(object : Callback<List<GitProjectEntity>> {
                    override fun onResponse(
                        call: Call<List<GitProjectEntity>>,
                        response: Response<List<GitProjectEntity>>
                    ) {
                        emitter.onSuccess(response.body())
                    }

                    override fun onFailure(call: Call<List<GitProjectEntity>>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
        }*/
        return api.listRepos(username)

    }

}