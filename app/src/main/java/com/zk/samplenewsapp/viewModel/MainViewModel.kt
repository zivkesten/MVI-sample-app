package com.zk.samplenewsapp.viewModel

import androidx.lifecycle.*
import com.zk.samplenewsapp.model.Articles
import com.zk.samplenewsapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }
}

class MainViewModel(private val repository: Repository): ViewModel() {

    private val newsLiveData = MutableLiveData<Articles?>()

    suspend fun getNewsFromApi() {
        newsLiveData.postValue(repository.getNews())
    }

    val fragmentContent: LiveData<Articles?> = newsLiveData.switchMap { articles ->
        liveData(Dispatchers.IO) {
            emit(articles)
        }
    }
}