package com.example.domain.use_case.search

import com.example.domain.model.home.response.LatestNewsResponse
import com.example.domain.repository.ISearchRepository
import com.example.domain.use_case.base.UseCaseWithParams
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: ISearchRepository,
) :
    UseCaseWithParams<LatestNewsResponse?,SearchUseCase.Params >() {

    override suspend fun run(params: Params): LatestNewsResponse? {
        return searchRepository.search(params.query)
    }

    class Params(var query: String)



}