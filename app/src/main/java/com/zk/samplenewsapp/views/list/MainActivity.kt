package com.zk.samplenewsapp.views.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zk.samplenewsapp.R
import com.zk.samplenewsapp.viewModel.MainViewModel
import com.zk.samplenewsapp.views.detail.ArticleActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val model by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ArticleListFragment.newInstance(), ArticleListFragment::class.qualifiedName)
            .commit()

        model.itemClicked.observe(this, Observer {
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra(getString(R.string.extra_item), it)
            startActivity(intent)
        })
    }
}