package com.bronski.android.scidtesttask.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [DataEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}