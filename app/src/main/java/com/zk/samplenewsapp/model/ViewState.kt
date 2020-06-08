package com.zk.samplenewsapp.model

data class DetailViewState(
    val backDrop: String? = null,
    val title: String = "",
    val description: String = ""
)

class ListViewState(
    val adapterList: List<Article> = emptyList()
)

sealed class ViewEffect {
    data class NavigateToLink(val url: String?) : ViewEffect()
    data class ShowSnackBar(val messageResource: Int) : ViewEffect()
    data class TransitionToScreen(val article: Article) : ViewEffect()
}

sealed class Event {
    object AddToFavouritesClicked : Event()
    object LinkClicked : Event()
    object SwipeToRefreshEvent: Event()
    object ScreenLoad: Event()
    data class DataReceived(val data: Article?) : Event()
    data class ListItemClicked(val item: Article): Event()
}