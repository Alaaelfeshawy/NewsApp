package com.example.domain.use_case.room

import com.example.domain.model.home.Article
import com.example.domain.repository.IRoomDBRepository
import com.example.domain.use_case.base.UseCaseWithParams

import javax.inject.Inject

class DeleteArticleFromDBUseCase @Inject constructor(private val dbRepository: IRoomDBRepository,
) :UseCaseWithParams<Int?, DeleteArticleFromDBUseCase.Params >() {
    override suspend fun run(params: Params) : Int?{
        return dbRepository.delete(params.article)
    }

    class Params(
        var article: Article,
    )


}