package com.example.newsapp.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiError
import com.example.domain.model.home.response.HomeResponse
import com.example.domain.use_case.base.UseCaseCallback
import com.example.domain.use_case.home.GetHomeDataUseCase
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.util.ErrorMessageUtils
import com.example.newsapp.util.SingleLiveEvent
import com.example.newsapp.util.StateListener
import com.example.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel  @Inject constructor(
    stateListener: StateListener,
    errorMessageUtils: ErrorMessageUtils,
    private val getHomeDataUseCase: GetHomeDataUseCase,
) :  BaseViewModel(
    stateListener,
    errorMessageUtils,
) {
     val homeData: SingleLiveEvent<List<ArticleModel>> = SingleLiveEvent()
     val error: SingleLiveEvent<String> = SingleLiveEvent()
     val noInternet: SingleLiveEvent<Boolean> = SingleLiveEvent()
     var filteredList = SingleLiveEvent<Pair<ArrayList<ArticleModel>,String>>()

    fun getHomeData(){
        stateListener.loading.value = true
        getHomeDataUseCase.invoke(
            viewModelScope,
            GetHomeDataUseCase.Params("the-next-web"),
            object : UseCaseCallback<HomeResponse?> {
                override fun onSuccess(t: HomeResponse?) {
                    Log.d(TAG, "onSuccess: ${t}")
                    stateListener.loading.value = false
                }

                override fun onError(throwable: ApiError) {
                    stateListener.errorMessage.value = throwable.getErrorMessage()
                    stateListener.loading.value = false
                }

            },
        )
    }

}