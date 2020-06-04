package com.zk.samplenewsapp.mainLIst

import com.zk.samplenewsapp.model.Article

interface OnItemClickListener {
    fun onItemClick(item: Article)
}