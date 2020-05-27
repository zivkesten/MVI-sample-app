package com.zk.samplenewsapp

import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * HelloApplication - Application Class
 * use HelloService
 */
class ClassThatExtendsKoinComponent : KoinComponent {

    // Inject HelloService
    private val helloService by inject<SomeService>()

    // display our data
    fun sayHello(): String = "Hey i am a KoinComponent child and i have a message from the service for you: \n${helloService.sayHello()}"
}
