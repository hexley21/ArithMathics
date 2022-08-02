package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

/**
 * Preference use-case that provides Welcome-field save method.
 */
class SaveTheme(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(default: Int) {
        preferenceRepository.saveTheme(default)
    }
}