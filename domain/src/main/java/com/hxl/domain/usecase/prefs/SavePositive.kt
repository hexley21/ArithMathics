package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class SavePositive(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(value: Boolean) {
        preferenceRepository.savePositive(value)
    }
}