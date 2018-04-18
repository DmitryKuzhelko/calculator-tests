package dmitry.kuzhelko.calculator.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import dmitry.kuzhelko.calculator.router.Router
import dmitry.kuzhelko.calculator.router.RouterImpl
import dmitry.kuzhelko.calculator.storage.ExampleDatabase
import dmitry.kuzhelko.calculator.storage.LocalStorageImpl
import dmitry.kuzhelko.calculator.ui.history.HistoryAdapter
import dmitry.kuzhelko.calculator.ui.history.presenter.HistoryPresenterImpl
import dmitry.kuzhelko.calculator.ui.main.presenter.MainPresenterImpl
import dmitry.kuzhelko.calculator.ui.main.view.Calculator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMainPresenter(storage: LocalStorageImpl, router: Router) =
            MainPresenterImpl(storage,
                    router,
                    Schedulers.io(),
                    AndroidSchedulers.mainThread())

    @Singleton
    @Provides
    fun provideHistoryPresenter(storage: LocalStorageImpl, router: Router) =
            HistoryPresenterImpl(storage,
                    router,
                    Schedulers.io(),
                    AndroidSchedulers.mainThread())

    @Singleton
    @Provides
    fun provideLocalStorage(database: ExampleDatabase): LocalStorageImpl = LocalStorageImpl(database)

    @Singleton
    @Provides
    fun provideDatabase(application: Context): ExampleDatabase = Room.databaseBuilder(application, ExampleDatabase::class.java, "examples_db").build()

    @Singleton
    @Provides
    fun provideCalculator(): Calculator = Calculator()

    @Provides
    fun provideAdapter(context: Context): HistoryAdapter = HistoryAdapter(context)

    @Singleton
    @Provides
    fun provideRouter(context: Context): Router = RouterImpl(context)
}