package com.hxl.domain.usecase.prefs

import com.hxl.domain.models.Custom
import com.hxl.domain.repository.CustomRepository

class GetCustom(private val customRepository: CustomRepository) {

    operator fun invoke(): Custom {
        return customRepository.getCustom()
    }
}