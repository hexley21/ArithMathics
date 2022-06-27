package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class SaveTheme(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(default: Int) {
        preferenceRepository.saveTheme(default)
    }
}