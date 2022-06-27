package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class GetWelcome(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(): Boolean {
        return preferenceRepository.getWelcome()
    }
}