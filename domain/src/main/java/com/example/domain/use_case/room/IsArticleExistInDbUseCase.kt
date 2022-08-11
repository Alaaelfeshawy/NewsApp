package com.example.domain.use_case.room

import com.example.domain.repository.IRoomDBRepository
import com.example.domain.use_case.base.UseCaseWithParams
import javax.inject.Inject

class IsArticleExistInDbUseCase @Inject constructor(
    private val dbRepository: IRoomDBRepository,
) :
    UseCaseWithParams<Boolean?, IsArticleExistInDbUseCase.Params>() {

    override suspend fun run(params: Params): Boolean? {
        return dbRepository.isArticleExsitInDb(params.url)
    }

    class Params(
        var url: String,
    )
}