package com.bronski.android.scidtesttask.list.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bronski.android.scidtesttask.core.data.retrofit.ScidApi
import com.bronski.android.scidtesttask.core.data.room.DataDao
import com.bronski.android.scidtesttask.core.data.room.DataEntity
import com.bronski.android.scidtesttask.core.utils.toDataEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ListRemoteMediator @Inject constructor(
    private val scidApi: ScidApi,
    private val dataDao: DataDao
) : RemoteMediator<Int, DataEntity>() {

    private var pageIndex = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DataEntity>
    ): MediatorResult {

        pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)
        val limit = state.config.pageSize

        return try {
            val responseApi = scidApi.getUsersResult(pageIndex)
            dataDao.saveData(responseApi.responseResult.resultData.toDataEntity())
            MediatorResult.Success(endOfPaginationReached = responseApi.responseResult.resultData.size < limit)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }
}