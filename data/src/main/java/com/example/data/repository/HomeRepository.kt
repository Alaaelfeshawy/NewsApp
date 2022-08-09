package com.example.data.repository

import com.example.data.source.remote.HomeApi
import com.example.domain.model.home.Article
import com.example.domain.model.home.response.HomeResponse
import com.example.domain.repository.IHomeRepository
import javax.inject.Inject

class HomeRepository(
    private val homeApi: HomeApi,
    private var fromTest : Boolean = false) : BaseRepository(fromTest) , IHomeRepository{
    @Inject constructor(homeApi: HomeApi) : this(homeApi,fromTest =false)

    override suspend fun homeData(source: String): HomeResponse {
        return homeApi.getHomeData(source , apiToken).let {
            HomeResponse(it.status , it.source , it.sortBy , it.articles?.map {
                Article(it.author , it.title , it.description , it.url , it.urlToImage , it.publishedAt)
            })
        }
    }
}