package com.hxl.domain.usecase.database.difficulty

import com.hxl.domain.models.Difficulty
import com.hxl.domain.repository.DifficultyRepository
import io.reactivex.rxjava3.core.Single

/**
 * custom Difficulty use-case that provides Difficulty record.
 */
class ReadDifficulty(private val difficultyRepository: DifficultyRepository) {
    operator fun invoke(): Single<Difficulty> {
        return difficultyRepository.readDifficulty()
    }
}