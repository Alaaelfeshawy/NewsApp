package com.example.newsapp.util

import javax.inject.Inject

class StateListener @Inject constructor() {
    val errorMessage: SingleLiveEvent<String> = SingleLiveEvent()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val success: SingleLiveEvent<String> = SingleLiveEvent()
}