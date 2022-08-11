package com.example.newsapp.ui.search

import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiError
import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.use_case.base.UseCaseCallback
import com.example.domain.use_case.room.AddArticleToDBUseCase
import com.example.domain.use_case.room.DeleteArticleFromDBUseCase
import com.example.domain.use_case.room.IsArticleExistInDbUseCase
import com.example.domain.use_case.search.SearchUseCase
import com.example.domain.use_case.shared_pref.GetAppModeUseCase
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.model.home.response.LatestNewsResponseModelMapper
import com.example.newsapp.ui.base.BaseViewModel
import com.example.newsapp.util.SingleLiveEvent
import com.example.newsapp.util.StateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    stateListener: StateListener,
    private val addArticleToDBUseCase: AddArticleToDBUseCase,
    private val isArticleExistInDbUseCase: IsArticleExistInDbUseCase,
    private val deleteArticleFromDBUseCase: DeleteArticleFromDBUseCase,
    private val searchUseCase: SearchUseCase,
    private val getAppModeUseCase: GetAppModeUseCase,
    ) :  BaseViewModel(
    stateListener,
    addArticleToDBUseCase,
    isArticleExistInDbUseCase,
    deleteArticleFromDBUseCase,
    getAppModeUseCase
) {
     val searchResults: SingleLiveEvent<List<ArticleModel>> = SingleLiveEvent()
     val noInternet: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun search(query:String){
        stateListener.loading.value = true
        searchUseCase.invoke(
            viewModelScope,
            listOf(SearchUseCase.Params(query)),
            object : UseCaseCallback<LatestNewsResponse?> {
                override fun onSuccess(t: ArrayList<LatestNewsResponse?>) {
                    val combineLists = ArrayList<ArticleModel>()
                    t.forEach {
                        combineLists.addAll(LatestNewsResponseModelMapper.mapper.fromDomain(it).articles as ArrayList)
                    }
                    searchResults.value = combineLists
                    stateListener.loading.value = false
                }
                override fun onError(throwable: ApiError) {
                    stateListener.errorMessage.value = throwable.getErrorMessage()
                    noInternet.value = throwable.errorStatus == ApiError.ErrorStatus.NO_CONNECTION
                    stateListener.loading.value = false
                }


            },
        )
    }


}