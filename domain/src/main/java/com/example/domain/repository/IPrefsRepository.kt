package com.example.domain.repository

interface IPrefsRepository {
    fun isDarkModeOn(value: Boolean)
    fun getAppMode(): Boolean
}