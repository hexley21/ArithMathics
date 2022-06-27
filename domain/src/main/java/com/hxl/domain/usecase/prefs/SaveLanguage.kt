package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class SaveLanguage(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(language: String){
        preferenceRepository.saveLanguage(language)
    }
}