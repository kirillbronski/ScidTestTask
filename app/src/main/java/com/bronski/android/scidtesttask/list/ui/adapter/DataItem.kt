package com.bronski.android.scidtesttask.list.ui.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataItem(
    val id: Int,
    val date: String,
    val description: String,
    val name: String
) : Parcelable
