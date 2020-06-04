package com.zk.samplenewsapp.views.list

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
	private val viewModel by sharedViewModel<MainViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val binding = FragmentArticleListBinding.inflate(inflater, container, false)

		binding.swiperefresh.setOnRefreshListener(this)
		observeNews()
		binding.list.apply {
			layoutManager = LinearLayoutManager(context)
			addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.list_item_decoration)))
			adapter = articlesAdapter
		}

		if (savedInstanceState == null) {
			viewModel.getNewsFromApi()
		}

		return binding.root
	}

	private fun observeNews() {
		viewModel.fragmentContent.observe(viewLifecycleOwner, Observer<Articles?> { articles ->
			Log.d(TAG, "Data: $articles")
			articles?.let { articlesAdapter.update(articles.articles as ArrayList<Article>) }
			swiperefresh.isRefreshing = false
		})
	}

	override fun onRefresh() {
		viewModel.getNewsFromApi()
	}

	override fun onItemClick(item: Article) {
		//Log.d("Zivi", "tap: "+item.title)
		viewModel.itemClicked(item)
	}
}
