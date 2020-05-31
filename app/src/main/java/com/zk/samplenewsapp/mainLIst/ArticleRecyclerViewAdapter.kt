package com.zk.samplenewsapp.mainLIst

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.zk.samplenewsapp.R
import com.zk.samplenewsapp.model.Article

class ArticleRecyclerViewAdapter(private var values: List<Article>? = null) : RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

    fun update(articles: List<Article>) {
        values = articles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = values?.get(position)
        holder.primaryText.text = article?.title
        holder.subText.text = article?.description
        Picasso.get().load(article?.urlToImage).into(holder.image)
    }

    override fun getItemCount(): Int {
        return values?.size?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val primaryText: TextView = view.findViewById(R.id.primary_text)
        val subText: TextView = view.findViewById(R.id.sub_text)
        val image: ImageView = view.findViewById(R.id.media_image)

        override fun toString(): String {
            return super.toString() + " '" + primaryText.text + "'"
        }
    }
}