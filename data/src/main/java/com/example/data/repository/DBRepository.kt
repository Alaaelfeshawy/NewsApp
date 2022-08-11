package com.example.data.repository

import com.example.data.entity.home.ArticleEntityMapper
import com.example.data.source.local.room.AppDatabase
import com.example.data.source.local.room.dao.article.ArticleDao
import com.example.data.source.local.room.entity.home.ArticleDataMapper
import com.example.data.util.ArticleDataConverter
import com.example.domain.model.home.Article
import com.example.domain.model.room.entity.ArticleDB
import com.example.domain.repository.IRoomDBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DBRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val articleDao: ArticleDao,
) : IRoomDBRepository {
    override suspend fun addArticle(article: Article?) : Long? {
        val articleEntity = ArticleEntityMapper.mapper.fromDomain(article)
        val res = ArticleDataConverter().convertFrom(articleEntity)
        return res?.let { articleDao.insertArticle(it) }
    }

    override fun getAllArticles(): Flow<List<ArticleDB>?>? {
        val list =  articleDao.getAllArticle()?.map {
            ArticleDataMapper.mapper.fromDomainList(it)
        }
        return list
    }
    override suspend fun delete(article :Article) : Int?{
        val articleEntity = ArticleEntityMapper.mapper.fromDomain(article)
        val res = ArticleDataConverter().convertFrom(articleEntity)
       return res?.let { articleDao.delete(it) }
    }

    override suspend fun isArticleExsitInDb(url :String) : Boolean?{
        return articleDao.exists(url)
    }

}