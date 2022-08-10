package com.example.newsapp.model.home.response

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.newsapp.model.home.ArticleModel
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

data class LatestNewsResponseModel (
    var status :String ? =null,
    var totalResults : Int ?= null,
    var articles: List<ArticleModel> ? =null
)

@Mapper
interface LatestNewsResponseModelMapper {
    fun fromDomain(domain: LatestNewsResponse?): LatestNewsResponseModel
    fun fromListDomain(domain: List<LatestNewsResponse?>?): List<LatestNewsResponseModel>
    fun toDomain(data: LatestNewsResponseModel?): LatestNewsResponse

    companion object {
        var mapper: LatestNewsResponseModelMapper =
            Mappers.getMapper(LatestNewsResponseModelMapper::class.java)
    }
}