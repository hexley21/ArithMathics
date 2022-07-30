package com.hxl.domain.usecase.database.difficulty

import com.hxl.domain.models.QuestionDifficulty
import com.hxl.domain.repository.DifficultyRepository

class InsertDifficulty(private val difficultyRepository: DifficultyRepository) {
    operator fun invoke(questionDifficulty: QuestionDifficulty) {
        difficultyRepository.insertCustom(questionDifficulty)
    }
}