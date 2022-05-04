package by.romanovich.myretrofitrx.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.romanovich.myretrofitrx.app

import by.romanovich.myretrofitrx.databinding.ActivityReposBinding

class ReposActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposBinding
    //MVVM
    private val viewModel: ReposViewModel by viewModels { ReposViewModelFactory(app.gitProjectsRepo) }
    private val adapter = GitProjectsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReposBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initOutgoingEvents()
        initIncomingEvents()
    }


    private fun initViews() {
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setHasStableIds(true)
        //передаем адаптер
        binding.reposRecyclerView.adapter = adapter
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
    }

}