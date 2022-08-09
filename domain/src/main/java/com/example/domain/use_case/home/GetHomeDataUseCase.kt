package com.example.domain.use_case.home

import com.example.domain.model.home.response.HomeResponse
import com.example.domain.repository.IHomeRepository
import com.example.domain.use_case.base.UseCaseWithParams
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
    private val homeRepository: IHomeRepository,
) :
    UseCaseWithParams<HomeResponse?,GetHomeDataUseCase.Params >() {

    override suspend fun run(params: Params): HomeResponse? {
        return homeRepository.homeData(params.source)
    }

    class Params(var source: String)



}