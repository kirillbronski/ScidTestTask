package com.bronski.android.scidtesttask.core.utils

import com.bronski.android.scidtesttask.core.data.retrofit.ResultData
import com.bronski.android.scidtesttask.core.data.room.DataEntity
import com.bronski.android.scidtesttask.list.ui.adapter.DataItem

fun DataEntity.toDataItem(): DataItem =
    DataItem(id = id, description = description, name = name, date = date)

fun List<ResultData>.toDataEntity(): List<DataEntity> = map {
    DataEntity(id = it.id, description = it.description, name = it.name, date = it.date)
}