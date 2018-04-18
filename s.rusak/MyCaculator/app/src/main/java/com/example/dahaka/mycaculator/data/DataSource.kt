package com.example.dahaka.mycaculator.data

import android.arch.lifecycle.LiveData
import com.example.dahaka.mycaculator.model.History

interface DataSource {
    fun loadHistory(): LiveData<List<History>>

    fun saveToHistory(time: Long, expr: String)

    fun clearDataFromDb()
}