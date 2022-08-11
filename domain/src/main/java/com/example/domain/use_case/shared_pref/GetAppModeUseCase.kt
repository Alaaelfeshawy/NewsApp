package com.example.domain.use_case.shared_pref

import com.example.domain.repository.IPrefsRepository
import javax.inject.Inject

class GetAppModeUseCase @Inject constructor(
    private val prefsRepository: IPrefsRepository,
)  {
     fun getAppMode():Boolean{
         return prefsRepository.getAppMode()
    }
}