package com.example.data.factory

import com.example.data.entity.home.response.TopNewsResponseEntity
import com.example.domain.model.home.response.TopNewsResponse

object TopNewsResponseEntityFactory {
    const val NUM_ATTRIBUTES=4

    fun generateDataForTopNewsResponseEntity(): TopNewsResponseEntity {
        return TopNewsResponseEntity(
            "status",
            "source",
            "sortBy",
            listOf(ArticleEntityFactory.generateDataForArticleEntity())
        )
    }

    fun generateDataForTopNewsResponseDomain(): TopNewsResponse {
        return TopNewsResponse(
            "status",
            "source",
            "sortBy",
            listOf(ArticleEntityFactory.generateDataForArticleDomain())
        )
    }
}