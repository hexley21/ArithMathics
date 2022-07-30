package com.hxl.domain.usecase.database.difficulty

import com.hxl.domain.models.Difficulty
import com.hxl.domain.repository.DifficultyRepository

class InsertDifficulty(private val difficultyRepository: DifficultyRepository) {
    operator fun invoke(difficulty: Difficulty) {
        difficultyRepository.insertCustom(difficulty)
    }
}