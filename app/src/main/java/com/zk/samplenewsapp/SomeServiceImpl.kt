package com.zk.samplenewsapp

/**
 * Hello Service - interface
 */
interface SomeService {
    fun sayHello(): String
}


/**
 * Hello Service Impl
 * Will use HelloMessageData data
 */
class SomeServiceImpl(private val helloMessageData: HelloMessageData) : SomeService {

    override fun sayHello() = "Hey from service i have a message for you, its: \n${helloMessageData.message}"
}
