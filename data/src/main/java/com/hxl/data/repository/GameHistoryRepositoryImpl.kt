package com.hxl.data.repository

import com.hxl.data.model.GameResultDto
import com.hxl.data.storage.room.dao.GameHistoryDao
import com.hxl.domain.models.GameResult
import com.hxl.domain.repository.GameHistoryRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.util.*

class GameHistoryRepositoryImpl(private val gameHistoryDao: GameHistoryDao) :
    GameHistoryRepository {

    override fun readGameHistory(): Single<List<GameResult>> {
        return gameHistoryDao.readGameResult()
            .map { array -> array.map { GameResultDto.toGameResult(it) } }
    }

    override fun insertGameResult(gameHistory: GameResult): Completable {
        return gameHistoryDao.insertGameResult(
            GameResultDto(
                Date().time,
                gameHistory.difficulty,
                gameHistory.levels,
                gameHistory.corrects,
                gameHistory.time
            )
        )
    }

}