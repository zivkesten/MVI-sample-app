package com.zk.samplenewsapp.views.list

import android.os.Bundle
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
import com.zk.samplenewsapp.model.Event
import com.zk.samplenewsapp.model.ListViewState
import com.zk.samplenewsapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ArticleListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

	companion object {
		fun newInstance(): ArticleListFragment {
			return ArticleListFragment()
		}
	}

	private lateinit var binding: FragmentArticleListBinding

	private val articlesAdapter: ArticleRecyclerViewAdapter = ArticleRecyclerViewAdapter(listener = this)

	// Lazy Inject ViewModel
	private val viewModel by sharedViewModel<MainViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentArticleListBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupBinding()
		observeViewState()
		if (savedInstanceState == null) {
			viewModel.event(Event.ScreenLoadEvent)
		}
	}

	private fun setupBinding() {
		binding.swiperefresh.setOnRefreshListener(this)
		binding.list.apply {
			layoutManager = LinearLayoutManager(context)
			addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.list_item_decoration)))
			adapter = articlesAdapter
		}
	}

	private fun observeViewState() {
		viewModel.obtainState.observe(viewLifecycleOwner, Observer {
			render(it)
		})
	}

	private fun render(state: ListViewState) {
		articlesAdapter.update(state.adapterList)
		swiperefresh.isRefreshing = false
	}

	override fun onRefresh() {
		viewModel.event(Event.Refresh)
	}

	override fun onItemClick(item: Article) {
		viewModel.event(Event.ItemClicked(item))
	}
}
