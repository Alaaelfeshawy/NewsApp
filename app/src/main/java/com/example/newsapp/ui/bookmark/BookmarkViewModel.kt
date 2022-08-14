package com.example.newsapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.example.domain.use_case.room.AddArticleToDBUseCase
import com.example.domain.use_case.room.DeleteArticleFromDBUseCase
import com.example.domain.use_case.room.GetArticleFromDBUseCase
import com.example.domain.use_case.room.IsArticleExistInDbUseCase
import com.example.domain.use_case.shared_pref.GetAppModeUseCase
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.model.room.entity.ArticleModelMapper
import com.example.newsapp.ui.base.BaseViewModel
import com.example.newsapp.util.StateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel  @Inject constructor(
    stateListener: StateListener,
    private val addArticleToDBUseCase: AddArticleToDBUseCase,
    private val getArticleFromDBUseCase: GetArticleFromDBUseCase,
    private val deleteArticleFromDBUseCase: DeleteArticleFromDBUseCase,
    private val isArticleExistInDbUseCase: IsArticleExistInDbUseCase,
    private val getAppModeUseCase: GetAppModeUseCase,
    ) :  BaseViewModel(
    stateListener,
    addArticleToDBUseCase,
    isArticleExistInDbUseCase,
    deleteArticleFromDBUseCase,
    getAppModeUseCase
) {

    val articles : LiveData<List<ArticleModel>> =
        getArticleFromDBUseCase.getAllArticles()?.asLiveData()?.map {
            ArticleModelMapper.mapper.fromDomainList(it)
        }!!

}