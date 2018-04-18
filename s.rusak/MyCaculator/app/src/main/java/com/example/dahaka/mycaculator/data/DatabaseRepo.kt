package com.example.dahaka.mycaculator.data

import android.arch.lifecycle.LiveData
import com.example.dahaka.mycaculator.model.History
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepo @Inject constructor(private val historyDao: HistoryDao) : DataSource {

    override fun loadHistory(): LiveData<List<History>> {
        return historyDao.getHistory()
    }

    override fun saveToHistory(time: Long, expr: String) {
        Executors.newSingleThreadExecutor().execute { historyDao.insertExpression(History(time, expr)) }
    }

    override fun clearDataFromDb() {
        Executors.newSingleThreadExecutor().execute { historyDao.clearDb() }
    }
}