package com.zk.samplenewsapp.di

import com.zk.samplenewsapp.viewModel.ArticleViewModel
import com.zk.samplenewsapp.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModeslModule {
	companion object{
		val modules = module {
			viewModel { MainViewModel(get()) }

			viewModel { ArticleViewModel() }
		}
	}
}
