package com.example.domain.use_case.room

import com.example.domain.model.home.Article
import com.example.domain.repository.IRoomDBRepository
import com.example.domain.use_case.base.UseCaseWithParams
import javax.inject.Inject

class AddArticleToDBUseCase @Inject constructor(
    private val dbRepository: IRoomDBRepository,
) :
    UseCaseWithParams<Long?, AddArticleToDBUseCase.Params>() {

    override suspend fun run(params: Params): Long? {
        return dbRepository.addArticle(params.article)
    }

    class Params(
        var article: Article,
    )
}