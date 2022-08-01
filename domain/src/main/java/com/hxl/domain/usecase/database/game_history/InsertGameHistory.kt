package com.hxl.domain.usecase.database.game_history

import com.hxl.domain.models.GameResult
import com.hxl.domain.repository.GameHistoryRepository
import io.reactivex.rxjava3.core.Completable

class InsertGameHistory(private val gameHistoryRepository: GameHistoryRepository) {

    operator fun invoke(gameHistory: GameResult): Completable{
        return gameHistoryRepository.insertGameResult(gameHistory)
    }
}