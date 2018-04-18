package com.example.dahaka.mycaculator

import android.app.Application
import com.example.dahaka.mycaculator.di.component.AppComponent
import com.example.dahaka.mycaculator.di.component.DaggerAppComponent

class App : Application() {

    internal val daggerComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent.inject(this)
    }
}