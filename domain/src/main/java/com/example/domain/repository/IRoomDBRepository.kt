package com.example.domain.repository

import com.example.domain.model.home.Article
import com.example.domain.model.room.entity.ArticleDB
import kotlinx.coroutines.flow.Flow

interface IRoomDBRepository {
    fun getAllArticles(): Flow<List<ArticleDB>?>?
    suspend fun addArticle(article: Article?) : Long?
    suspend fun delete(article :Article) :Int?
    suspend fun isArticleExsitInDb(url: String): Boolean?
}