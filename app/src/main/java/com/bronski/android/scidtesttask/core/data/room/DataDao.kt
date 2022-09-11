package com.bronski.android.scidtesttask.core.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {

    @Query("SELECT * FROM data_table")
    fun getPagingSource(): PagingSource<Int, DataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveData(dataEntity: List<DataEntity>)
}