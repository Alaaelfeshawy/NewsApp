package com.example.data.entity.home.response

import com.example.data.entity.home.ArticleEntity
import com.example.domain.model.home.response.LatestNewsResponse
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

data class LatestNewsResponseEntity (
    var status :String ? =null,
    var totalResults : Int ?= null,
    var articles: List<ArticleEntity> ? =null
)

@Mapper
interface LatestNewsResponseEntityMapper {
    fun fromDomain(domain: LatestNewsResponse?): LatestNewsResponseEntity
    fun toDomain(data: LatestNewsResponseEntity?): LatestNewsResponse

    companion object {
        var mapper: LatestNewsResponseEntityMapper =
            Mappers.getMapper(LatestNewsResponseEntityMapper::class.java)
    }
}