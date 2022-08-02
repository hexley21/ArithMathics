package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

/**
 * Preference use-case that provides Timer-field.
 */
class GetTimer(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(): Boolean {
        return preferenceRepository.getTimer()
    }
}