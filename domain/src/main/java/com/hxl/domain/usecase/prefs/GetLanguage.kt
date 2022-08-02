package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

/**
 * Preference use-case that provides Language-field.
 */
class GetLanguage(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(): String {
        return preferenceRepository.getLanguage()
    }
}