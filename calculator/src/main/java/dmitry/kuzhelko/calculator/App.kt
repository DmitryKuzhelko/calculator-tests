package dmitry.kuzhelko.calculator

import android.app.Application
import dmitry.kuzhelko.calculator.di.AppComponent
import dmitry.kuzhelko.calculator.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }
}