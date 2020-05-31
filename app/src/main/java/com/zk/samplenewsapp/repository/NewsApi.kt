package com.zk.samplenewsapp.repository

import com.zk.samplenewsapp.model.Articles
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

val apiModule = module {
    single {
        val retrofit: Retrofit = get()
        retrofit.create(NewsApi::class.java)
    }
}

class Constants {
    companion object {
        const val KEY = "cec6b7a9137b4863bf1fc13cb3301627"
    }
}

interface NewsApi {
    @GET("top-headlines?country=il")
    suspend fun getNews(@Query("apiKey") key: String = Constants.KEY): Articles?
}
