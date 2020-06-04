package com.zk.samplenewsapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zk.samplenewsapp.R
import com.zk.samplenewsapp.viewModel.MainViewModel
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
            intent.putExtra("ziv", it)
            Log.d("Zivi", "start activity")
            startActivity(intent)
        })
    }
}
