package com.example.newsapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiError
import com.example.domain.model.home.Article
import com.example.domain.use_case.base.UseCaseCallback
import com.example.domain.use_case.room.AddArticleToDBUseCase
import com.example.domain.use_case.room.DeleteArticleFromDBUseCase
import com.example.domain.use_case.room.IsArticleExistInDbUseCase
import com.example.domain.use_case.shared_pref.GetAppModeUseCase
import com.example.newsapp.util.StateListener

abstract class BaseViewModel(
    var stateListener: StateListener,
    private val addArticleToDBUseCase: AddArticleToDBUseCase,
    private val isArticleExistInDbUseCase: IsArticleExistInDbUseCase,
    private val deleteArticleFromDBUseCase: DeleteArticleFromDBUseCase,
    private val getAppModeUseCase: GetAppModeUseCase,
    ) : ViewModel() {

    fun addArticleToDB(article: Article){
        addArticleToDBUseCase.invoke(
            viewModelScope,
            listOf(AddArticleToDBUseCase.Params(article)),
            object : UseCaseCallback<Long?> {
                override fun onSuccess(t: ArrayList<Long?>) {
                    stateListener.success.value = "Added To bookmark successfully"
                    article.id = t[0]
                    article.saved = true
                }

                override fun onError(throwable: ApiError) {
                    stateListener.errorMessage.value = throwable.getErrorMessage()
                    stateListener.loading.value = false
                }
            }
        )
    }

    fun isArticleExistInDbAnUpdate(article: Article, isExist :((Boolean)->Unit)?=null){
        isArticleExistInDbUseCase.invoke(
            viewModelScope,
            listOf(IsArticleExistInDbUseCase.Params(article.url ?: "")),
            object : UseCaseCallback<Boolean?> {
                override fun onSuccess(t: ArrayList<Boolean?>) {
                    if (t[0] == true){
                        deleteArticleFromDb(article)
                        isExist?.invoke(false)
                    }else{
                        addArticleToDB(article)
                        isExist?.invoke(true)
                    }
                }

                override fun onError(throwable: ApiError) {
                    stateListener.errorMessage.value = throwable.getErrorMessage()
                    stateListener.loading.value = false
                }
            }
        )
    }

    fun deleteArticleFromDb (article: Article){
        deleteArticleFromDBUseCase.invoke(viewModelScope,
            listOf(DeleteArticleFromDBUseCase.Params(article)),
            object : UseCaseCallback<Int?> {
                override fun onSuccess(t: ArrayList<Int?>) {
                    stateListener.success.value = "Article deleted Successfully"
                    article.saved = false
                }

                override fun onError(throwable: ApiError) {
                    stateListener.errorMessage.value = throwable.getErrorMessage()
                }

            }
        )
    }

    fun isArticleExistInDb(article: Article, isExist :((Boolean)->Unit)?=null){
        isArticleExistInDbUseCase.invoke(
            viewModelScope,
            listOf(IsArticleExistInDbUseCase.Params(article.url ?: "")),
            object : UseCaseCallback<Boolean?> {
                override fun onSuccess(t: ArrayList<Boolean?>) {
                    if (t[0] == true){
                        isExist?.invoke(true)
                    }else{
                        isExist?.invoke(false)
                    }
                }

                override fun onError(throwable: ApiError) {
                    stateListener.errorMessage.value = throwable.getErrorMessage()
                    stateListener.loading.value = false
                }
            }
        )
    }

    fun getAppMode() : Boolean{
        return getAppModeUseCase.getAppMode()
    }

    override fun onCleared() {
        super.onCleared()
        stateListener.loading.value = false
    }
}