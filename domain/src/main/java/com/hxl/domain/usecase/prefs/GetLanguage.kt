package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class GetLanguage(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(): String {
        return preferenceRepository.getLanguage()
    }
}