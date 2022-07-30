package com.hxl.domain.usecase.database.difficulty

import com.hxl.domain.models.Difficulty
import com.hxl.domain.repository.DifficultyRepository

class ReadDifficulty(private val difficultyRepository: DifficultyRepository) {

    operator fun invoke(): Difficulty {
        return difficultyRepository.readCustom()
    }
}