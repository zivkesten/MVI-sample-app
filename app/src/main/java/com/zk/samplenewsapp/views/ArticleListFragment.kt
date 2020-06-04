package com.zk.samplenewsapp.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zk.samplenewsapp.R
import com.zk.samplenewsapp.databinding.FragmentArticleListBinding
import com.zk.samplenewsapp.mainLIst.ArticleRecyclerViewAdapter
import com.zk.samplenewsapp.mainLIst.OnItemClickListener
import com.zk.samplenewsapp.mainLIst.VerticalSpaceItemDecoration
import com.zk.samplenewsapp.model.Article
import com.zk.samplenewsapp.model.Articles
import com.zk.samplenewsapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ArticleListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

	companion object {
		val TAG = ArticleListFragment::class.qualifiedName
		fun newInstance(): ArticleListFragment {
			return ArticleListFragment()
		}
	}

	private val articlesAdapter: ArticleRecyclerViewAdapter = ArticleRecyclerViewAdapter(listener = this)

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