package com.example.newsapp.ui.news_details

import com.example.domain.use_case.room.AddArticleToDBUseCase
import com.example.domain.use_case.room.DeleteArticleFromDBUseCase
import com.example.domain.use_case.room.GetArticleFromDBUseCase
import com.example.domain.use_case.room.IsArticleExistInDbUseCase
import com.example.domain.use_case.shared_pref.GetAppModeUseCase
import com.example.newsapp.ui.base.BaseViewModel
import com.example.newsapp.util.StateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel  @Inject constructor(
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
}