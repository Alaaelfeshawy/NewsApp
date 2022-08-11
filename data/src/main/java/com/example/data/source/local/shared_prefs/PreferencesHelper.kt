package com.example.data.source.local.shared_prefs

interface PreferencesHelper {
    fun isDarkModeOn(value: Boolean)
    fun getAppMode(): Boolean
}