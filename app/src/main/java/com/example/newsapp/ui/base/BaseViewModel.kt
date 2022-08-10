package com.example.newsapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.newsapp.util.ErrorMessageUtils
import com.example.newsapp.util.StateListener

abstract class BaseViewModel(
    var stateListener: StateListener,
    val errorMessageUtils: ErrorMessageUtils,
) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        stateListener.loading.value = false
    }
}