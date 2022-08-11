package com.example.data.source.local.room.dao.article

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.source.local.room.AppDatabase
import com.example.data.source.local.room.dao.BaseDao
import com.example.data.source.local.room.entity.home.ArticleData
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ArticleDao(private val appDatabase: AppDatabase) : BaseDao<ArticleData?> {

    @Transaction
    @Query("SELECT * FROM articles")
    abstract fun  getAllArticle(): Flow<List<ArticleData>>?

    @Transaction
    open suspend fun insertArticle (article: ArticleData) : Long?{
        return appDatabase.articleDao().insert(article)
    }

    @Transaction
    open suspend fun delete(article: ArticleData) : Int{
        return appDatabase.articleDao().deleteArticle(article)
    }

    @Transaction
    @Query("SELECT EXISTS (SELECT 1 FROM articles WHERE url = :url)")
   abstract suspend fun exists(url: String): Boolean

}