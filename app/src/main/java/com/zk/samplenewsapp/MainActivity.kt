package com.zk.samplenewsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Lazy Inject ViewModel
    private val viewModel: MainViewModel by viewModel()

    // Inject NetworkService
    private val injectedService by inject<SomeService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Using the KoinComponent
        text1.text = ClassThatExtendsKoinComponent().sayHello()
        //Using the viewModel that receives a service in its constructor
        text2.text = viewModel.sayHello()
        //Using the service injected directly into the view
        text3.text = injectedService.sayHello()

    }
}
