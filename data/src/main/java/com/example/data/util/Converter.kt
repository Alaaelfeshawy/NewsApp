package com.example.data.util

import com.example.data.entity.home.ArticleEntity
import com.example.data.source.local.room.entity.home.ArticleData

class ArticleDataConverter {
    fun convertFrom(articleModel: ArticleEntity?): ArticleData? {
        if (articleModel == null) return null
        return ArticleData(
            articleModel.id,
            articleModel.author,
            articleModel.title ?: "",
            articleModel.description,
            articleModel.url,
            articleModel.urlToImage,
            articleModel.publishedAt,
            articleModel.saved
        )

    }
    fun convertTo(articleModel: ArticleData?): ArticleEntity? {
        if (articleModel == null) return null
        return ArticleEntity(
            articleModel.id,
            null,
            articleModel.author,
            articleModel.title,
            articleModel.description,
            articleModel.url,
            articleModel.urlToImage,
            articleModel.publishedAt
        )

    }
}