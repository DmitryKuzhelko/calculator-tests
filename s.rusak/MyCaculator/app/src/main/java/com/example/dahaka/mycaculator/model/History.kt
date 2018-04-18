package com.example.dahaka.mycaculator.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "history")
data class History(
        @PrimaryKey
        @ColumnInfo(name = "timestamp")
        var timestamp: Long,

        @ColumnInfo(name = "expression")
        var expression: String)