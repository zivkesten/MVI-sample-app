package com.zk.samplenewsapp.views

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.zk.samplenewsapp.ArticleFragment
import com.zk.samplenewsapp.ArticleViewModel
import com.zk.samplenewsapp.R
import com.zk.samplenewsapp.databinding.ActivityArticleBinding
import com.zk.samplenewsapp.model.Article
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleActivity : AppCompatActivity() {

    private val viewModel by viewModel<ArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        viewModel.article(intent.getParcelableExtra("ziv"))
        supportFragmentManager
            .beginTransaction()
            .add(R.id.article_container, ArticleFragment.newInstance())
            .commit()
    }
}