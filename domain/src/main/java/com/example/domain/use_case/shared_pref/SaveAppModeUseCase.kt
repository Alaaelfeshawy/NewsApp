package com.example.domain.use_case.shared_pref

import com.example.domain.repository.IPrefsRepository
import javax.inject.Inject

class SaveAppModeUseCase @Inject constructor(
    private val prefsRepository: IPrefsRepository,
)  {
     fun saveAppMode(value: Boolean){
         prefsRepository.isDarkModeOn(value)
    }
}