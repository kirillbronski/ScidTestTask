package com.bronski.android.scidtesttask.list.repository

import androidx.paging.*
import com.bronski.android.scidtesttask.core.data.room.DataDao
import com.bronski.android.scidtesttask.core.utils.Constants.PAGE_SIZE
import com.bronski.android.scidtesttask.core.utils.toDataItem
import com.bronski.android.scidtesttask.list.ui.adapter.DataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class ListRepositoryImpl @Inject constructor(
    private val listRemoteMediator: ListRemoteMediator,
    private val dataDao: DataDao
) : IListRepository {

    override fun getResult(): Flow<PagingData<DataItem>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = listRemoteMediator,
            pagingSourceFactory = {
                dataDao.getPagingSource()
            })
            .flow
            .map { pagingData ->
                pagingData.map { dataEntity ->
                    dataEntity.toDataItem()
                }
            }
}