package com.hxl.domain.usecase.database.difficulty

import com.hxl.domain.models.Difficulty
import com.hxl.domain.repository.DifficultyRepository
import io.reactivex.rxjava3.core.Completable

class InsertDifficulty(private val difficultyRepository: DifficultyRepository) {
    operator fun invoke(difficulty: Difficulty): Completable {
        return difficultyRepository.insertDifficulty(difficulty)
    }
}