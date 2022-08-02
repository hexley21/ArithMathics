package com.hxl.data.repository

import com.hxl.data.model.DifficultyDto
import com.hxl.data.storage.room.dao.DifficultyDao
import com.hxl.domain.models.Difficulty
import com.hxl.domain.repository.DifficultyRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Repository implementation that handles custom Difficulty object.
 */
class DifficultyRepositoryImpl(private val difficultyDao: DifficultyDao) : DifficultyRepository {

    override fun readDifficulty(): Single<Difficulty> {
        return difficultyDao.getDifficulty().map { DifficultyDto.toDifficulty(it) }
    }

    override fun insertDifficulty(difficulty: Difficulty): Completable {
        return difficultyDao.insertDifficulty(
            DifficultyDto(
                1,
                difficulty.levels,
                difficulty.operations,
                difficulty.numberRange.first,
                difficulty.numberRange.last,
                difficulty.operators,
                difficulty.time
            )
        )
    }
}