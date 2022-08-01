package com.hxl.domain.usecase.database.game_history

import com.hxl.domain.models.GameResult
import com.hxl.domain.repository.GameHistoryRepository
import io.reactivex.rxjava3.core.Single

class ReadGameHistory(private val gameHistoryRepository: GameHistoryRepository) {

    operator fun invoke(): Single<List<GameResult>> {
        return gameHistoryRepository.readGameHistory()
    }
}