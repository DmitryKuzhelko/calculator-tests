package com.example.dahaka.mycaculator.di.component

import android.app.Application
import com.example.dahaka.mycaculator.App
import com.example.dahaka.mycaculator.activity.MainActivity
import com.example.dahaka.mycaculator.di.module.MainModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MainModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(activity: MainActivity)


}