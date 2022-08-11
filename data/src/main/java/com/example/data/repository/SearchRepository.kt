package com.example.data.repository

import com.example.data.entity.home.response.LatestNewsResponseEntityMapper
import com.example.data.source.remote.SearchApi
import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.repository.ISearchRepository
import javax.inject.Inject

class SearchRepository(
    private val searchApi: SearchApi,
    private var fromTest : Boolean = false) :
    BaseRepository(fromTest) , ISearchRepository{
    @Inject constructor(searchApi: SearchApi) :
            this(searchApi,fromTest =false)

    override suspend fun search(query: String): LatestNewsResponse {
       return searchApi.search(query,apiToken).let {
           LatestNewsResponseEntityMapper.mapper.toDomain(it)
       }
    }
}