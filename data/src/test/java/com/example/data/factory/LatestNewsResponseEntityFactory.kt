package com.example.data.factory

import com.example.data.entity.home.response.LatestNewsResponseEntity
import com.example.domain.model.home.response.LatestNewsResponse

object LatestNewsResponseEntityFactory {
    const val NUM_ATTRIBUTES=3

    fun generateDataForLatestNewsNewsResponseEntity(): LatestNewsResponseEntity {
        return LatestNewsResponseEntity(
            "status",
            1,
            listOf(ArticleEntityFactory.generateDataForArticleEntity())
        )
    }

    fun generateDataForLatestNewsNewsResponseDomain(): LatestNewsResponse {
        return LatestNewsResponse(
            "status",
            1,
            listOf(ArticleEntityFactory.generateDataForArticleDomain())
        )
    }
}