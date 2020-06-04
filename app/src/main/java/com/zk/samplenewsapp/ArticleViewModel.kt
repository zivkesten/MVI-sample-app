package com.zk.samplenewsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zk.samplenewsapp.model.*

class ArticleViewModel : ViewModel() {

    private var item: Article? = null

    private val viewState = MutableLiveData<ViewState>()

    private val viewAction = MutableLiveData<ViewEffect>()

    val obtainState: LiveData<ViewState> = viewState

    val obtainAction: LiveData<ViewEffect> = viewAction

    fun article(article: Article?) {

        article?.let {
            val state = ViewState(
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
            is Event.screenLoadEvent -> Log.d("Zivi", "article screen loaded")
            is Event.tapLink -> viewAction.postValue(ViewEffect.OpenLinkExternally(item))
            is Event.addToHistoryEvent -> viewAction.postValue(ViewEffect.ShowSnackBar(R.string.button_result_text))
        }
    }
}