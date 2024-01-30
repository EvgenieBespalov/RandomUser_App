package com.example.user_list

import android.app.Application
import com.example.user_list.domain.di.UserListComponent
import di.provideRetrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                provideRetrofitModule(),
            )
        }
    }
}