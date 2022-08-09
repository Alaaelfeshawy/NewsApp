package com.example.domain.repository

import com.example.domain.model.home.response.HomeResponse

interface IHomeRepository {
    suspend fun homeData(source: String): HomeResponse?
}