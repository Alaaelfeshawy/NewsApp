package com.example.data.source.local.shared_prefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(
    context: Context,
    prefFileName: String?,
) : PreferencesHelper {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun isDarkModeOn(value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_DARK_MODE_ON, value)
        editor.apply()
    }
    override fun getAppMode(): Boolean {
        return sharedPreferences.getBoolean(IS_DARK_MODE_ON, false)
    }
    companion object {
        private const val IS_DARK_MODE_ON = "IS_DARK_MODE_ON"

    }



}