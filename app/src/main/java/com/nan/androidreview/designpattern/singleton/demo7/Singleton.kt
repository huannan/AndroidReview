package com.nan.androidreview.designpattern.singleton.demo7

/**
 * Kotlin中通过object关键字可以实现最简单的单例
 * 特点：这种单例只有一个实现的对象；不能自定义构造方法；可以实现接口、继续父类
 */
object Singleton {
    public fun test() {
        println("")
    }
}

//调用方式
fun main(args: Array<String>) {
    Singleton.test()
}