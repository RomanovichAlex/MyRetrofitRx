package by.romanovich.myretrofitrx.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.romanovich.myretrofitrx.domain.GitProjectEntity

class GitProjectsAdapter : RecyclerView.Adapter<GitProjectVh>() {
    private var data: List<GitProjectEntity> = emptyList()

    //адаптер тупой поэтому заводим функцию setData, которая будет его обновлять
    fun setData(repos: List<GitProjectEntity>) {
        data = repos
        //обновляет все данные
        notifyDataSetChanged()
    }

    //создаем ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitProjectVh {
        return GitProjectVh.create(parent)
    }
    //привязываем ViewHolder, к итему который получаем по pos
    override fun onBindViewHolder(holder: GitProjectVh, position: Int) {
        holder.bind(getItem(position))
    }

    //позиция
    private fun getItem(pos: Int): GitProjectEntity = data[pos]

    //размер
    override fun getItemCount(): Int = data.size

}