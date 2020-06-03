package com.zk.samplenewsapp

import android.app.Application
import com.zk.samplenewsapp.networking.networkModule
import com.zk.samplenewsapp.repository.apiModule
import com.zk.samplenewsapp.repository.repositoryModule
import com.zk.samplenewsapp.viewModel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(viewModelModule,
                repositoryModule, apiModule,
                networkModule
            ))
        }
    }
}
