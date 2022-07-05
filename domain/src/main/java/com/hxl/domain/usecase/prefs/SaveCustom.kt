package com.hxl.domain.usecase.prefs

import com.hxl.domain.models.QuestionDifficulty
import com.hxl.domain.repository.CustomRepository

class SaveCustom(private val customRepository: CustomRepository) {

    operator fun invoke(questionDifficulty: QuestionDifficulty) {
        customRepository.saveCustom(questionDifficulty)
    }
}