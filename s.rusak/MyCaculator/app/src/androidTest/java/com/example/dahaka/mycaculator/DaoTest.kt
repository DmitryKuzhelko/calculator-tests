package com.example.dahaka.mycaculator

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.dahaka.mycaculator.data.AppDatabase
import com.example.dahaka.mycaculator.data.HistoryDao
import com.example.dahaka.mycaculator.model.History
import com.example.dahaka.mycaculator.util.getValueBlocking
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoTest {
    private lateinit var database: AppDatabase
    private lateinit var historyDao: HistoryDao

    @Before
    @Throws(Exception::class)
    fun createDatabase() {
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(), AppDatabase::class.java)
                .build()
        historyDao = database.historyDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun checkDataTest() {
        val historyObject = History(1L, "test")
        historyDao.insertExpression(historyObject)
        val historyLiveData = historyDao.getHistory()
        val historyFromDb = historyLiveData.getValueBlocking()
        assertEquals(1, historyFromDb?.size)
        assertTrue(historyFromDb!![0].expression == "test")
    }
}