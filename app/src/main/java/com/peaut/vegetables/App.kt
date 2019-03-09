package com.peaut.vegetables

import android.app.Application

/**
 * @author peaut
 * @package  com.peaut.vegetables
 * @fileName App
 * @date on  2019/2/25  11:28
 */
class App: Application(){
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}