package com.example.data.repository

import com.example.data.entity.home.response.LatestNewsResponseEntityMapper
import com.example.data.entity.home.response.TopNewsResponseEntityMapper
import com.example.data.source.remote.HomeApi
import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.model.home.response.TopNewsResponse
import com.example.domain.repository.IHomeRepository
import com.example.domain.util.AppConstants
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Named

class HomeRepository(
    private val homeApi: HomeApi,
    private val homeApi2: HomeApi,
    private var fromTest : Boolean = false) :
    BaseRepository(fromTest) , IHomeRepository{
    @Inject constructor(@Named(AppConstants.HOME_API_VERSION_1) homeApi: HomeApi,
                        @Named(AppConstants.HOME_API_VERSION_2) homeApi2: HomeApi) :
            this(homeApi,homeApi2,fromTest =false)
    override suspend fun getTopNews(country: String): TopNewsResponse {
        return homeApi2.getTopNews(country , apiToken).let {
            TopNewsResponseEntityMapper.mapper.toDomain(it)
        }
    }
    override suspend fun getLatestNews(source: String): LatestNewsResponse {
        return homeApi.getLatestNews(source , apiToken).let {
            LatestNewsResponseEntityMapper.mapper.toDomain(it)
        }
    }
}