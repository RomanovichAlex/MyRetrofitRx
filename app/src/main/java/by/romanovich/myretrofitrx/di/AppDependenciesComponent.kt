package by.romanovich.myretrofitrx.di

import by.romanovich.myretrofitrx.domain.ProjectsRepo
import by.romanovich.myretrofitrx.ui.ReposActivity
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton


//объединяет несколько модулей

@Singleton
@Component(
    //обявляем модуль
    modules = [
        AppDependenciesModule::class
    //,AppDependenciesModule2::class,AppDependenciesModule3::class
    ]
)
interface AppDependenciesComponent {
    //указываем откуда брать зависимости и куда их класть, у нас только ReposActivity
    fun inject(reposActivity: ReposActivity)
    //генерирует метод который возращает значение username
    @Named("username")
    fun getDefaultUserName(): String
    fun getProjectsRepo(): ProjectsRepo
}