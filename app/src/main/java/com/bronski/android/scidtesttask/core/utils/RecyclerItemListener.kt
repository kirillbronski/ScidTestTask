package com.bronski.android.scidtesttask.core.utils

import com.bronski.android.scidtesttask.list.ui.adapter.DataItem

interface RecyclerItemListener {
    fun onItemClick(itemData: DataItem)
}