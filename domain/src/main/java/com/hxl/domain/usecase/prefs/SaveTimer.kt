package com.hxl.domain.usecase.prefs

import com.hxl.domain.repository.PreferenceRepository

class SaveTimer(private val preferenceRepository: PreferenceRepository) {

    operator fun invoke(welcome: Boolean){
        preferenceRepository.saveTimer(welcome)
    }
}