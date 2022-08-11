package com.example.data.source.remote


import com.example.data.entity.home.response.LatestNewsResponseEntity
import com.example.data.entity.home.response.TopNewsResponseEntity
import retrofit2.http.*
import javax.inject.Named

interface SearchApi {
    @GET("everything")
    suspend fun search(@Query("q") query: String? , @Query("apiKey") apiKey : String): LatestNewsResponseEntity

}