package com.example.factory

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.newsapp.model.home.response.LatestNewsResponseModel

object LatestNewsResponseModelFactory {
    const val NUM_ATTRIBUTES=3

    fun generateDataForLatestNewsResponseModel(): LatestNewsResponseModel {
        return LatestNewsResponseModel(
            "status",
            1,
            listOf(ArticleModelFactory.generateDataForArticleModel())
        )
    }

    fun generateDataForLatestNewsResponseDomain(): LatestNewsResponse {
        return LatestNewsResponse(
            "status",
            1,
            listOf(ArticleModelFactory.generateDataForArticleDomain())
        )
    }
}