package com.bronski.android.scidtesttask.core.state

sealed class ViewState {
    object DefaultState : ViewState()
    object LoadingState : ViewState()
    object SuccessState : ViewState()
}
