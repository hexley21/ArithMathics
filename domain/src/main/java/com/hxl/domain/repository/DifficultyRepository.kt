package com.hxl.domain.repository

import com.hxl.domain.models.Difficulty
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Difficulty Repository interface for handling custom difficulty.
 */
interface DifficultyRepository {

    fun readDifficulty(): Single<Difficulty>

    fun insertDifficulty(difficulty: Difficulty): Completable

}