package com.example.factory

import com.example.domain.model.home.response.TopNewsResponse
import com.example.newsapp.model.home.response.TopNewsResponseModel

object TopNewsResponseModelFactory {
    const val NUM_ATTRIBUTES=4

    fun generateDataForTopNewsResponseModel(): TopNewsResponseModel {
        return TopNewsResponseModel(
            "status",
            "source",
            "sortBy",
            listOf(ArticleModelFactory.generateDataForArticleModel())
        )
    }

    fun generateDataForTopNewsResponseDomain(): TopNewsResponse {
        return TopNewsResponse(
            "status",
            "source",
            "sortBy",
            listOf(ArticleModelFactory.generateDataForArticleDomain())
        )
    }
}