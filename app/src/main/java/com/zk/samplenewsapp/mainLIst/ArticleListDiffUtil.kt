package com.zk.samplenewsapp.mainLIst

import androidx.recyclerview.widget.DiffUtil
import com.zk.samplenewsapp.model.Article

class ArticleListDiffUtil(private var newArticles: List<Article>, private var oldArticles: List<Article>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldArticles.size
    }

    override fun getNewListSize(): Int {
        return newArticles.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldArticles[oldItemPosition] === newArticles[newItemPosition]
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldArticles[oldItemPosition] == newArticles[newItemPosition]
    }
    
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}