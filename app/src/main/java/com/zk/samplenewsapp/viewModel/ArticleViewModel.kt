package com.zk.samplenewsapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zk.samplenewsapp.R
import com.zk.samplenewsapp.model.*

class ArticleViewModel : ViewModel() {

    private var item: Article? = null

    private val viewState = MutableLiveData<DetailViewState>()

    private val viewAction = MutableLiveData<ViewEffect>()

    val obtainStateDetail: LiveData<DetailViewState> = viewState

    val obtainAction: LiveData<ViewEffect> = viewAction

    fun article(article: Article?) {

        article?.let {
            val state = DetailViewState(
                article.urlToImage,
                article.title?: "",
                article.description?: ""
            )
            item = article
            viewState.postValue(state)
        }
    }

    fun event(event: Event) {
        when(event) {
            is Event.ScreenLoadEvent -> Log.d("Zivi", "article screen loaded")
            is Event.ClickLink -> viewAction.postValue(ViewEffect.OpenLinkExternally(item))
            is Event.AddToFavouritesEvent -> viewAction.postValue(ViewEffect.ShowSnackBar(
                R.string.button_result_text
            ))
        }
    }
}