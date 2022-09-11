package com.bronski.android.scidtesttask.core.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
class DataEntity(
    @PrimaryKey
    val id: Int,
    val date: String,
    val description: String,
    val name: String
)