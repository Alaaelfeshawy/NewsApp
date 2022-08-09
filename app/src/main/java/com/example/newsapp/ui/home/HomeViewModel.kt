package com.example.newsapp.ui.home

import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiError
import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.model.home.response.TopNewsResponse
import com.example.domain.use_case.base.UseCaseCallback
import com.example.domain.use_case.home.GetLatestNewsUseCase
import com.example.domain.use_case.home.GetTopNewsUseCase
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.model.home.response.LatestNewsResponseModelMapper
import com.example.newsapp.model.home.response.TopNewsResponseModelMapper
import com.example.newsapp.util.ErrorMessageUtils
import com.example.newsapp.util.SingleLiveEvent
import com.example.newsapp.util.StateListener
import com.example.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    stateListener: StateListener,
    errorMessageUtils: ErrorMessageUtils,
    private val getLatestNewsUseCase: GetLatestNewsUseCase,
    private val getTopNewsUseCase: GetTopNewsUseCase,
) :  BaseViewModel(
    stateListener,
    errorMessageUtils,
) {
     val homeData: SingleLiveEvent<List<ArticleModel>> = SingleLiveEvent()
     val topNews: SingleLiveEvent<List<ArticleModel>> = SingleLiveEvent()
     val error: SingleLiveEvent<String> = SingleLiveEvent()
     val noInternet: SingleLiveEvent<Boolean> = SingleLiveEvent()
     var filteredList = SingleLiveEvent<Pair<ArrayList<ArticleModel>,String>>()

    fun getLatestNews(){
        stateListener.loading.value = true
        getLatestNewsUseCase.invoke(
            viewModelScope,
            listOf(GetLatestNewsUseCase.Params("the-next-web"),
                GetLatestNewsUseCase.Params("bbc-news")),
            object : UseCaseCallback<LatestNewsResponse?> {
                override fun onSuccess(t: ArrayList<LatestNewsResponse?>) {
                    val combineLists = ArrayList<ArticleModel>()
                    t.forEach {
                       combineLists.addAll(LatestNewsResponseModelMapper.mapper.fromDomain(it).articles as ArrayList)
                    }
                    homeData.value = combineLists
                    stateListener.loading.value = false
                }
                override fun onError(throwable: ApiError) {
                    stateListener.errorMessage.value = throwable.getErrorMessage()
                    stateListener.loading.value = false
                }


            },
        )
    }

    fun getTopNews(){
        stateListener.loading.value = true
        getTopNewsUseCase.invoke(
            viewModelScope,
            listOf(GetTopNewsUseCase.Params("eg")),
            object : UseCaseCallback<TopNewsResponse?> {
                override fun onSuccess(t: ArrayList<TopNewsResponse?>) {
                    val combineLists = ArrayList<ArticleModel>()
                    t.forEach {
                        combineLists.addAll(TopNewsResponseModelMapper.mapper.fromDomain(it).articles as ArrayList)
                    }
                    topNews.value = combineLists
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