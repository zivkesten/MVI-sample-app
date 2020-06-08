package com.zk.samplenewsapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zk.samplenewsapp.R
import com.zk.samplenewsapp.model.*

class ArticleViewModel : ViewModel() {

    private var data: Article? = null

    private val viewState = MutableLiveData<DetailViewState>()

    private val viewAction = MutableLiveData<ViewEffect>()

    val obtainStateDetail: LiveData<DetailViewState> = viewState

    val obtainAction: LiveData<ViewEffect> = viewAction

    private var currentViewState = DetailViewState()
        set(value) {
            viewState.value = value
            field = value
        }

    private fun handleScreenLoadState(article: Article?) {
        article?.let {
            currentViewState =
                currentViewState.copy(
                backDrop = article.urlToImage,
                title = article.title?: "",
                description = article.description?: ""
            )
            data = article
        }
    }

    fun event(event: Event) {
        when(event) {
            is Event.DataReceived -> handleScreenLoadState(event.data)
            is Event.LinkClicked -> viewAction.postValue(ViewEffect.NavigateToLink(data?.url))
            is Event.AddToFavouritesClicked -> viewAction.postValue(ViewEffect.ShowSnackBar(
                R.string.button_result_text
            ))
        }
    }
}