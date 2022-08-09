package com.example.newsapp.util

import javax.inject.Inject

class StateListener @Inject constructor() {
    val errorMessage: SingleLiveEvent<String> = SingleLiveEvent()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun setErrorMessage(errorMessage: String?) {
        this.errorMessage.value = errorMessage
    }
}