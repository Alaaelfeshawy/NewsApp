package com.example.factory

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.newsApp.model.home.response.HomeResponseModel

object HomeResponseModelFactory {
    const val NUM_ATTRIBUTES=4

    fun generateDataForHomeResponseModel(): HomeResponseModel {
        return HomeResponseModel(
            "status",
            "source",
            "sortBy",
            listOf(ArticleModelFactory.generateDataForArticleModel())
        )
    }

    fun generateDataForHomeResponseDomain(): LatestNewsResponse {
        return LatestNewsResponse(
            "status",
            "source",
            "sortBy",
            listOf(ArticleModelFactory.generateDataForArticleDomain())
        )
    }
}