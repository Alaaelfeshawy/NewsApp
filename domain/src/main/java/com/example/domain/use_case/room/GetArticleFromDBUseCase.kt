package com.example.domain.use_case.room

import com.example.domain.model.room.entity.ArticleDB
import com.example.domain.repository.IRoomDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticleFromDBUseCase @Inject constructor(private val dbRepository: IRoomDBRepository) {
    fun getAllArticles() : Flow<List<ArticleDB>?>?{
        return dbRepository.getAllArticles()
    }
}