package com.zk.samplenewsapp.model

class ViewState(
    val backDrop: String? = null,
    val title: String = "",
    val description: String = ""
)

sealed class ViewEffect {
    data class OpenLinkExternally(val article: Article?) : ViewEffect()
    data class ShowSnackBar(val messageResource: Int) : ViewEffect()
}

sealed class Event {
    object screenLoadEvent : Event()
    object addToHistoryEvent : Event()
    object tapLink : Event()
}