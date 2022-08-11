package com.example.domain.repository

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.model.home.response.TopNewsResponse
import kotlinx.coroutines.Deferred


interface ISearchRepository {
    suspend fun search(query: String): LatestNewsResponse
}