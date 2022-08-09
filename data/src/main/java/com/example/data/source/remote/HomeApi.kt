package com.example.data.source.remote


import com.example.data.entity.home.response.LatestNewsResponseEntity
import com.example.data.entity.home.response.TopNewsResponseEntity
import retrofit2.http.*
import javax.inject.Named

interface HomeApi {
//    @Named("First_api")
    @GET("articles")
    suspend fun getLatestNews(@Query("source") source: String? , @Query("apiKey") apiKey : String): LatestNewsResponseEntity

//    @Named("Second_api")
    @GET("top-headlines")
    suspend fun getTopNews(@Query("country") country: String? , @Query("apiKey") apiKey : String): TopNewsResponseEntity
}