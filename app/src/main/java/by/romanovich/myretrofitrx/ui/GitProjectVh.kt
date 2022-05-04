package by.romanovich.myretrofitrx.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.romanovich.myretrofitrx.databinding.ItemGitProjectBinding
import by.romanovich.myretrofitrx.domain.GitProjectEntity

class GitProjectVh(private val binding: ItemGitProjectBinding) :
    RecyclerView.ViewHolder(binding.root) {

    //статический метод который принимает на вход, parent и возращает вместо него ItemGitProjectBinding
    companion object {
        fun create(parent: ViewGroup): GitProjectVh {
            val inflater = LayoutInflater.from(parent.context)
            return GitProjectVh(ItemGitProjectBinding.inflate(inflater))
        }
    }

    fun bind(item: GitProjectEntity) {
        binding.itemGitRepoId.text = item.id.toString()
        binding.itemGitRepoName.text = item.name
    }
}