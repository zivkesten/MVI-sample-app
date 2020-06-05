package com.zk.samplenewsapp

import android.app.Application
import com.zk.samplenewsapp.di.ViewModeslModule
import com.zk.samplenewsapp.networking.networkModule
import com.zk.samplenewsapp.repository.apiModule
import com.zk.samplenewsapp.repository.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)
            modules(listOf(
				ViewModeslModule.modules,
                repositoryModule,
				apiModule,
                networkModule
            ))
        }
    }
}
