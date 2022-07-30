package com.hxl.domain.usecase.database.difficulty

import com.hxl.domain.models.QuestionDifficulty
import com.hxl.domain.repository.DifficultyRepository

class ReadDifficulty(private val difficultyRepository: DifficultyRepository) {

    operator fun invoke(): QuestionDifficulty {
        return difficultyRepository.getCustom()
    }
}