package com.example.newsapp.ui.settings

import com.example.domain.use_case.room.AddArticleToDBUseCase
import com.example.domain.use_case.room.DeleteArticleFromDBUseCase
import com.example.domain.use_case.room.IsArticleExistInDbUseCase
import com.example.domain.use_case.shared_pref.GetAppModeUseCase
import com.example.domain.use_case.shared_pref.SaveAppModeUseCase
import com.example.newsapp.model.home.ArticleModel
import com.example.newsapp.ui.base.BaseViewModel
import com.example.newsapp.util.SingleLiveEvent
import com.example.newsapp.util.StateListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel  @Inject constructor(
    stateListener: StateListener,
    private val addArticleToDBUseCase: AddArticleToDBUseCase,
    private val isArticleExistInDbUseCase: IsArticleExistInDbUseCase,
    private val deleteArticleFromDBUseCase: DeleteArticleFromDBUseCase,
    private val saveAppModeUseCase: SaveAppModeUseCase,
    private val getAppModeUseCase: GetAppModeUseCase,

    ) :  BaseViewModel(
    stateListener,
    addArticleToDBUseCase,
    isArticleExistInDbUseCase,
    deleteArticleFromDBUseCase,
    getAppModeUseCase
) {

    fun saveAppMode(value:Boolean){
        saveAppModeUseCase.saveAppMode(value)
    }
}