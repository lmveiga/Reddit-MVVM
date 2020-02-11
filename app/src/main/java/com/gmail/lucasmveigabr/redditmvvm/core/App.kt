package com.gmail.lucasmveigabr.redditmvvm.core

import android.app.Application
import com.gmail.lucasmveigabr.redditmvvm.di.apiModule
import com.gmail.lucasmveigabr.redditmvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(apiModule, viewModelModule))
        }
    }
}