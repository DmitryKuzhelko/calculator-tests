package com.example.dahaka.mycaculator

import com.example.dahaka.mycaculator.data.DatabaseRepo
import com.example.dahaka.mycaculator.data.HistoryDao
import com.example.dahaka.mycaculator.data.HistoryDao_Impl
import com.example.dahaka.mycaculator.model.History
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepoTest {

    @Mock
    private lateinit var historyDao: HistoryDao
    private lateinit var databaseRepo: DatabaseRepo

    @Before
    fun init() {
        historyDao = Mockito.mock(HistoryDao_Impl::class.java)
        databaseRepo = DatabaseRepo(historyDao)
    }

    @Test
    fun writeData() {
        databaseRepo.saveToHistory(1L, "test")
        verify(historyDao).insertExpression(History(1L, "test"))
    }

    @Test
    fun getDataTest() {
        databaseRepo.loadHistory()
        verify(historyDao).getHistory()
    }

    @Test
    fun clearData() {
        databaseRepo.clearDataFromDb()
        verify(historyDao).clearDb()
    }
}