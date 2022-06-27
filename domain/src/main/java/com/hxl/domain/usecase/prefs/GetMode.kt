package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class GetMode(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(): Int {
        return preferenceRepository.getMode()
    }
}