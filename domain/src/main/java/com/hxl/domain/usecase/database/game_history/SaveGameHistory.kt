package com.hxl.domain.usecase.database.game_history

import com.hxl.domain.models.GameResult
import com.hxl.domain.repository.GameHistoryRepository
import java.util.*

class SaveGameHistory(private val gameHistoryRepository: GameHistoryRepository) {

    operator fun invoke(gameHistory: Stack<GameResult>){
        gameHistoryRepository.saveGameResult(gameHistory)
    }
}