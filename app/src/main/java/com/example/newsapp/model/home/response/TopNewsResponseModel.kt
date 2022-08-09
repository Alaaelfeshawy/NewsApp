package com.example.newsapp.model.home.response

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.model.home.response.TopNewsResponse
import com.example.newsapp.model.home.ArticleModel
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

data class TopNewsResponseModel (
    var status :String ? =null,
    var source : String ?= null,
    var sortByLString: String ? =null,
    var articles: List<ArticleModel> ? =null
)

@Mapper
interface TopNewsResponseModelMapper {
    fun fromDomain(domain: TopNewsResponse?): TopNewsResponseModel
    fun fromListDomain(domain: List<TopNewsResponse?>?): List<TopNewsResponseModel>
    fun toDomain(data: TopNewsResponseModel?): TopNewsResponse

    companion object {
        var mapper: TopNewsResponseModelMapper =
            Mappers.getMapper(TopNewsResponseModelMapper::class.java)
    }
}