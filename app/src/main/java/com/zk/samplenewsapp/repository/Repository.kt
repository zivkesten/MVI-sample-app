package com.zk.samplenewsapp.repository

import com.zk.samplenewsapp.model.Articles
import org.koin.dsl.module

val repositoryModule = module {

    single { Repository(get()) }
}

class Repository(private val newsApi: NewsApi)  {

   suspend fun getNews(): Articles? {
       return newsApi.getNews()
   }
}
