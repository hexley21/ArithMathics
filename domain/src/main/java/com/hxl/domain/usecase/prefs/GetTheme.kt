package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class GetTheme(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(): Int {
        return preferenceRepository.getTheme()
    }
}