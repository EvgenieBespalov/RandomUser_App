package com.example.user_list

import android.app.Application
import di.DaggerRetrofitComponent
import di.RetrofitComponent

class MyApplication : Application() {

    lateinit var retrofitComponent: RetrofitComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        retrofitComponent = DaggerRetrofitComponent
            .builder()
            .build()
    }

}