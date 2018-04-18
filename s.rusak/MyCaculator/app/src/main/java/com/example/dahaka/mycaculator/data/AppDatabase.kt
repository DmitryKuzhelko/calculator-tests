package com.example.dahaka.mycaculator.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.dahaka.mycaculator.model.History

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}