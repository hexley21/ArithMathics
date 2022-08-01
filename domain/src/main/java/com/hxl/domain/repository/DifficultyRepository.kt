package com.hxl.domain.repository

import com.hxl.domain.models.Difficulty
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface DifficultyRepository {

    fun readDifficulty(): Single<Difficulty>

    fun insertDifficulty(difficulty: Difficulty): Completable

}