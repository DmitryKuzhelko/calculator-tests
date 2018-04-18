package com.example.dahaka.mycaculator

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.example.dahaka.mycaculator.data.DataSource
import com.example.dahaka.mycaculator.model.CalculatorViewModel
import com.example.dahaka.mycaculator.model.History
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {
    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    private lateinit var viewModel: CalculatorViewModel
    @Mock
    private lateinit var mockSource: DataSource

    @Before
    @Throws(Exception::class)
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = CalculatorViewModel(mockSource)
    }

    @Test
    @Throws(Exception::class)
    fun saveToDbTest() {
        viewModel.setNumber("4")
        viewModel.setOperator("+")
        viewModel.setNumber("2")
        viewModel.setResult("=")
        viewModel.showDisplay()
        verify(mockSource).saveToHistory(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString())
    }

    @Test
    fun readFromDbTest() {
        val stringLiveData = MutableLiveData<List<History>>()
        `when`(mockSource.loadHistory()).thenReturn(stringLiveData)
        viewModel.historyList()
        verify(mockSource).loadHistory()
    }
}