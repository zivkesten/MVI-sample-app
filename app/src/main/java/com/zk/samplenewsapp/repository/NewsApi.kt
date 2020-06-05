package com.zk.samplenewsapp.repository

import com.zk.samplenewsapp.model.Articles
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.http.GET

val apiModule = module {
    single {
        val retrofit: Retrofit = get()
        retrofit.create(NewsApi::class.java)
    }
}

interface NewsApi {
    @GET("top-headlines?country=us")
    suspend fun getNews(): Articles?
}
