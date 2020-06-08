package com.zk.samplenewsapp.viewModel

import androidx.lifecycle.*
import com.zk.samplenewsapp.model.*
import com.zk.samplenewsapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: Repository) : ViewModel() {

	private val viewState = MutableLiveData<ListViewState>()

	private val viewAction = MutableLiveData<ViewEffect>()

	val obtainState: LiveData<ListViewState> = viewState

	val obtainViewEffects: LiveData<ViewEffect> = viewAction

	fun event(event: Event) {
		when(event) {
			is Event.ScreenLoad, Event.SwipeToRefreshEvent -> getNewsFromApi()
			is Event.ListItemClicked -> viewAction.postValue(ViewEffect.TransitionToScreen(event.item))
		}
	}

	private fun getNewsFromApi() {
		viewModelScope.launch {
			val news = loadNewsFromApi()
			news?.let {
				val state = ListViewState(news.articles)
				viewState.postValue(state)
			}
		}
	}

	private suspend fun loadNewsFromApi() =
		withContext(Dispatchers.IO) {
			repository.getNews()
		}
}