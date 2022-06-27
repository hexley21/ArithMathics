package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class SaveWelcome(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(welcome: Boolean){
        preferenceRepository.saveWelcome(welcome)
    }
}