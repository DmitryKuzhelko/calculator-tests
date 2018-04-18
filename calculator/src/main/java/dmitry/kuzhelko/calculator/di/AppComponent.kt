package dmitry.kuzhelko.calculator.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dmitry.kuzhelko.calculator.ui.history.view.HistoryActivity
import dmitry.kuzhelko.calculator.ui.main.view.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(historyActivity: HistoryActivity)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(context: Context): Builder
    }
}