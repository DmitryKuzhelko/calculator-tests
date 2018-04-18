package com.example.dahaka.mycaculator.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.dahaka.mycaculator.model.History

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpression(history: History)

    @Query("SELECT * FROM history")
    fun getHistory(): LiveData<List<History>>

    @Query("DELETE FROM history")
    fun clearDb()
}