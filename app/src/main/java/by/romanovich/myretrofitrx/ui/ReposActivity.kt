package by.romanovich.myretrofitrx.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.romanovich.myretrofitrx.app
import by.romanovich.myretrofitrx.data.MockProjectsRepoImpl
import by.romanovich.myretrofitrx.databinding.ActivityReposBinding
import by.romanovich.myretrofitrx.domain.ProjectsRepo
import javax.inject.Inject

//ui - пользовательские интерфейсы связанные с этим подклассом, ui знает о domain, не знает о data.(Знает о бизнесс логике но не знает о реализации)
class ReposActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposBinding

//Dagger
    @Inject
    lateinit var gitProjectsRepo: ProjectsRepo


    //MVVM
    //private val gitRepo : ProjectsRepo by inject() /*by lazy { app.gitProjectsRepo}*/
    //viewModel создаем с помощбю отдельного расширения ReposViewModel by viewModels и в нее передаем наш репозиторий который достаем из app
    //private val viewModel: ReposViewModel by viewModel() /*{ ReposViewModelFactory(gitRepo) }*/

    //Dagger
    private val viewModel: ReposViewModel by viewModels {
        ReposViewModelFactory(gitProjectsRepo)
    }

    private val adapter = GitProjectsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Dagger
        //говорим локатору найди нам в этой активити все поля помеченные @Inject и сделай для них сет
        app.appDependenciesComponent.inject(this)

        initViews()
        initOutgoingEvents()
        initIncomingEvents()
    }


    private fun initViews() {
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setHasStableIds(true)
        //передаем адаптер
        binding.reposRecyclerView.adapter = adapter


        //Dagger
        binding.usernameEditText.setText(app.appDependenciesComponent.getDefaultUserName())
    }

    //события которые может отправить вьюшка
    private fun initOutgoingEvents() {
        binding.showButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            viewModel.onShowRepos(username)
        }
    }

    //подъписываемся, события которые приходят от вью модели, и обновляет вью
    private fun initIncomingEvents() {
        viewModel.repos.observe(this) {
            //приходят в адаптер и обновляют
            adapter.setData(it)
        }
        /*viewModel.inProgress.observe(this){
            if(it){
                binding.showButton.isEnabled = false
                binding.usernameEditText.isEnabled = false
            }else{
                binding.showButton.isEnabled = true
                binding.usernameEditText.isEnabled = true
            }
        }*/
        viewModel.inProgress.observe(this) { inProgress ->
            binding.showButton.isEnabled = ! inProgress
            binding.usernameEditText.isEnabled = ! inProgress
            binding.progressBarLayout.isVisible =  inProgress
        }
    }
}