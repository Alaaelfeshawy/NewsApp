package com.example.domain.repository

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.model.home.response.TopNewsResponse
import kotlinx.coroutines.Deferred


interface IHomeRepository {
    suspend fun getTopNews(country: String): TopNewsResponse
    suspend fun getLatestNews(source: String): LatestNewsResponse
}