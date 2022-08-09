package com.example.domain.use_case.home

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.model.home.response.TopNewsResponse
import com.example.domain.repository.IHomeRepository
import com.example.domain.use_case.base.UseCaseWithParams
import javax.inject.Inject

class GetTopNewsUseCase @Inject constructor(
    private val homeRepository: IHomeRepository,
) :
    UseCaseWithParams<TopNewsResponse?,GetTopNewsUseCase.Params >() {

    override suspend fun run(params: Params): TopNewsResponse? {
        return homeRepository.getTopNews(params.country)
    }

    class Params(var country: String)

}