package com.zk.samplenewsapp.repository

import com.zk.samplenewsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private val KEY = BuildConfig.NEWS_API_KEY

class AuthInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response =
		chain.proceed(
			chain.request().newBuilder()
				.url(
					chain.request().url.newBuilder()
						.addQueryParameter("apiKey", KEY)
						.build()
				)
				.build()
		)
}
