package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

/**
 * Preference use-case that provides Game-Mode-field.
 */
class GetMode(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(): Int {
        return preferenceRepository.getMode()
    }
}