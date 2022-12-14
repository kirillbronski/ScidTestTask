package com.bronski.android.scidtesttask.list.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bronski.android.scidtesttask.core.state.ViewState
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

    private val _viewState = MutableStateFlow<ViewState>(ViewState.DefaultState)
    val viewState: StateFlow<ViewState> get() = _viewState

    private val _list = MutableStateFlow<PagingData<DataItem>>(PagingData.empty())
    val list: StateFlow<PagingData<DataItem>> get() = _list

    init {
        getApiData()
    }

    fun getApiData() {
        _viewState.value = ViewState.LoadingState
        viewModelScope.launch(Dispatchers.IO) {
            listRepository.getResult().cachedIn(viewModelScope).collectLatest {
                _list.value = it
                _viewState.value = ViewState.SuccessState
            }
        }
    }

}