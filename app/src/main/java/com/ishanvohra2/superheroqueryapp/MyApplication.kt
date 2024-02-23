package com.ishanvohra2.superheroqueryapp

import android.app.Application
import com.ishanvohra2.superheroqueryapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }

}