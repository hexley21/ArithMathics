package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

/**
 * Preference use-case that provides Game-Mode-field save method.
 */
class SaveMode(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(mode: Int) {
        preferenceRepository.saveMode(mode)
    }
}