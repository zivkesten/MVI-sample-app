package com.zk.samplenewsapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zk.samplenewsapp.*
import com.zk.samplenewsapp.mainLIst.ArticleRecyclerViewAdapter
import com.zk.samplenewsapp.mainLIst.VerticalSpaceItemDecoration
import com.zk.samplenewsapp.model.Articles
import com.zk.samplenewsapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_article_list.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val LOGGER = ArticleListFragment::class.qualifiedName

    private val adapter: ArticleRecyclerViewAdapter =
        ArticleRecyclerViewAdapter()

    private val observer = Observer<Articles?> { articles ->
        Log.d(LOGGER, "Data: $articles")
        articles?.let { news ->  adapter.update(news.articles)}
        view?.swiperefresh?.isRefreshing = false
    }

    // Lazy Inject ViewModel
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article_list, container, false)
        viewModel.fragmentContent.observe(viewLifecycleOwner, observer)
        // Set the adapter
        val dividerItemDecoration =
            VerticalSpaceItemDecoration(30)
        view.list.layoutManager = LinearLayoutManager(context)
        view.list.addItemDecoration(dividerItemDecoration)
        view.list.adapter = adapter

        return view
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getNewsFromApi()
        }
    }

    companion object {
        fun newInstance(): ArticleListFragment {
            return ArticleListFragment()
        }
    }

    override fun onRefresh() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getNewsFromApi()
        }
    }
}