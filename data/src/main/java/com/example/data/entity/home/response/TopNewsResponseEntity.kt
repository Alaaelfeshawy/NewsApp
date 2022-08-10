package com.example.data.entity.home.response

import com.example.data.entity.home.ArticleEntity
import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.model.home.response.TopNewsResponse
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

data class TopNewsResponseEntity (
    var status :String ? =null,
    var source : String ?= null,
    var sortByLString: String ? =null,
    var articles: List<ArticleEntity> ? =null
)

@Mapper
interface TopNewsResponseEntityMapper {
    fun fromDomain(domain: TopNewsResponse?): TopNewsResponseEntity
    fun toDomain(data: TopNewsResponseEntity?): TopNewsResponse

    companion object {
        var mapper: TopNewsResponseEntityMapper =
            Mappers.getMapper(TopNewsResponseEntityMapper::class.java)
    }
}