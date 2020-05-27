package com.zk.samplenewsapp

import androidx.lifecycle.ViewModel

class MainViewModel(val someService: SomeService) : ViewModel() {

    fun sayHello() = "Hello i am the viewModel and i got this message from the service: \n${someService.sayHello()}"

}