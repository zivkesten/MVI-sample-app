package com.zk.samplenewsapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zk.samplenewsapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ArticleListFragment.newInstance(), ArticleListFragment::class.qualifiedName)
            .commit()
    }
}
