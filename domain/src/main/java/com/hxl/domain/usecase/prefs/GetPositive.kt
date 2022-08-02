package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

/**
 * Preference use-case that provides Positive-field.
 */
class GetPositive(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(): Boolean {
        return preferenceRepository.getPositive()
    }
}