package com.hxl.domain.usecase.game_history

import com.hxl.domain.models.GameResult
import com.hxl.domain.repository.GameHistoryRepository
import java.util.*

class GetGameHistory(private val gameHistoryRepository: GameHistoryRepository) {

    operator fun invoke(): Stack<GameResult> {
        return gameHistoryRepository.getGameHistory()
    }
}