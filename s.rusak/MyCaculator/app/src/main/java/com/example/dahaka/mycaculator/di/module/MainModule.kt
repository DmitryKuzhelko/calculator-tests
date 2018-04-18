package com.example.dahaka.mycaculator.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.example.dahaka.mycaculator.DB_NAME
import com.example.dahaka.mycaculator.data.AppDatabase
import com.example.dahaka.mycaculator.data.DatabaseRepo
import com.example.dahaka.mycaculator.data.HistoryDao
import com.example.dahaka.mycaculator.model.CalculatorViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideRepo(historyDao: HistoryDao) = DatabaseRepo(historyDao)

    @Provides
    @Singleton
    fun provideHistoryDao(db: AppDatabase) = db.historyDao()

    @Provides
    @Singleton
    fun provideDb(context: Application) =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideViewModel(databaseRepo: DatabaseRepo) = CalculatorViewModel(databaseRepo)
}