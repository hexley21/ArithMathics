package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class SaveMode(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(mode: Int) {
        preferenceRepository.saveMode(mode)
    }
}