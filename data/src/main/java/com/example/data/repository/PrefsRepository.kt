package com.example.data.repository

import com.example.data.source.local.shared_prefs.PreferencesHelper
import com.example.domain.repository.IPrefsRepository
import javax.inject.Inject

class PrefsRepository @Inject constructor(private val preferencesHelper: PreferencesHelper) :
    IPrefsRepository {
    override fun isDarkModeOn(value: Boolean) {
        preferencesHelper.isDarkModeOn(value)
    }

    override fun getAppMode(): Boolean {
        return preferencesHelper.getAppMode()
    }


}