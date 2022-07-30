package com.hxl.domain.usecase.database.difficulty

import com.hxl.domain.models.QuestionDifficulty
import com.hxl.domain.repository.CustomRepository

class InsertCustom(private val customRepository: CustomRepository) {

    operator fun invoke(questionDifficulty: QuestionDifficulty) {
        customRepository.insertCustom(questionDifficulty)
    }
}