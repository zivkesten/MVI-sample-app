package com.zk.samplenewsapp.model

class DetailViewState(
    val backDrop: String? = null,
    val title: String = "",
    val description: String = ""
)

class ListViewState(
    val adapterList: List<Article> = emptyList()
)

sealed class ViewEffect {
    data class OpenLinkExternally(val article: Article?) : ViewEffect()
    data class ShowSnackBar(val messageResource: Int) : ViewEffect()
    data class TransitionToScreen(val article: Article) : ViewEffect()
}

sealed class Event {
    object ScreenLoadEvent : Event()
    object AddToFavouritesEvent : Event()
    object ClickLink : Event()
    object Refresh: Event()
    data class ItemClicked(val article: Article): Event()
}