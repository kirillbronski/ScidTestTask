package com.bronski.android.scidtesttask.di.modules

import androidx.paging.ExperimentalPagingApi
import com.bronski.android.scidtesttask.list.repository.IListRepository
import com.bronski.android.scidtesttask.list.repository.ListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun bindListRepository(
        listRepositoryImpl: ListRepositoryImpl
    ): IListRepository
}