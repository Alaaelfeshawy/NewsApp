package com.example.domain.use_case.home

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.repository.IHomeRepository
import com.example.domain.use_case.base.UseCaseWithParams
import javax.inject.Inject

class GetLatestNewsUseCase @Inject constructor(
    private val homeRepository: IHomeRepository,
) :
    UseCaseWithParams<LatestNewsResponse?,GetLatestNewsUseCase.Params >() {

    override suspend fun run(params: Params): LatestNewsResponse? {
        return homeRepository.getLatestNews(params.source)
    }

    class Params(var source: String)



}