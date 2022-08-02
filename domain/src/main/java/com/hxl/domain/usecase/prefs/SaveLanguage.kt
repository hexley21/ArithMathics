package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

/**
 * Preference use-case that provides Language-field save method.
 */
class SaveLanguage(private val preferenceRepository: PreferenceRepository) {
    operator fun invoke(language: String){
        preferenceRepository.saveLanguage(language)
    }
}