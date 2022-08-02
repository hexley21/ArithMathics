package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

/**
 * Preference use-case that provides Positive-field save method.
 */
class SavePositive(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(value: Boolean) {
        preferenceRepository.savePositive(value)
    }
}