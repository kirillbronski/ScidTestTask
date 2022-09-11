package com.bronski.android.scidtesttask.list.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bronski.android.scidtesttask.list.repository.IListRepository
import com.bronski.android.scidtesttask.list.ui.adapter.DataItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listRepository: IListRepository
) : ViewModel() {

    private val _list = MutableStateFlow<PagingData<DataItem>>(PagingData.empty())
    val list: StateFlow<PagingData<DataItem>> get() = _list

    init {
        getApiData()
    }

    private fun getApiData() {
        viewModelScope.launch(Dispatchers.IO) {
            listRepository.getResult().cachedIn(viewModelScope).collectLatest {
                _list.value = it
            }
        }
    }

}