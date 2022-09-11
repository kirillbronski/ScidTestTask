package com.bronski.android.scidtesttask.list.repository

import androidx.paging.PagingData
import com.bronski.android.scidtesttask.list.ui.adapter.DataItem
import kotlinx.coroutines.flow.Flow

interface IListRepository {
    fun getResult(): Flow<PagingData<DataItem>>
}