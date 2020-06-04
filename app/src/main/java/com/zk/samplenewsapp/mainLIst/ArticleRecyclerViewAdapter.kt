package com.zk.samplenewsapp.mainLIst

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zk.samplenewsapp.databinding.ArticleListItemBinding
import com.zk.samplenewsapp.model.Article

class ArticleRecyclerViewAdapter(private var values: List<Article> = ArrayList(), private val listener: OnItemClickListener) : RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

    fun update(articles: ArrayList<Article>) {
        if (values.isEmpty()) {
            values = articles
            notifyDataSetChanged()
            return
        }
        val diffResult = DiffUtil.calculateDiff(ArticleListDiffUtil(values, articles))
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(values[position])

    override fun getItemCount(): Int {
        return values.size
    }

    inner class ViewHolder(private val binding: ArticleListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            with(binding) {
                primaryText.text = item.title
                subText.text = item.description
                Picasso.get().load(item.urlToImage).into(mediaImage)
                binding.root.setOnClickListener {
                    listener.onItemClick(item)
                }
            }
        }
    }
}