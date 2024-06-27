package com.example.mtgcards.base

import android.app.Application
import com.example.mtgcards.base.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule))
        }
    }
}