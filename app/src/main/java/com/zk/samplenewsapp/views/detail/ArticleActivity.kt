package com.zk.samplenewsapp.views.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zk.samplenewsapp.viewModel.ArticleViewModel
import com.zk.samplenewsapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleActivity : AppCompatActivity() {

    private val viewModel by viewModel<ArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        viewModel.article(intent.getParcelableExtra(getString(R.string.extra_item)))
        supportFragmentManager
            .beginTransaction()
            .add(R.id.article_container, ArticleFragment.newInstance())
            .commit()
    }
}