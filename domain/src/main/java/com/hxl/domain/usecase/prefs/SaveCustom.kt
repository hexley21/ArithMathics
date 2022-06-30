package com.hxl.domain.usecase.prefs

import com.hxl.domain.models.Custom
import com.hxl.domain.repository.CustomRepository

class SaveCustom(private val customRepository: CustomRepository) {

    operator fun invoke(custom: Custom) {
        customRepository.saveCustom(custom)
    }
}