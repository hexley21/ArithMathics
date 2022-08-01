package com.hxl.data.repository

import com.hxl.data.model.Json
import com.hxl.data.storage.InternalStorage
import com.hxl.domain.models.GameResult
import com.hxl.domain.repository.GameHistoryRepository
import java.util.*

class GameHistoryRepositoryImpl(private val internalStorage: InternalStorage): GameHistoryRepository {
    override fun readGameHistory(): Stack<GameResult> {
        return Json.toGameHistory(internalStorage.read("game_history"))
    }

    override fun insertGameResult(gameHistory: Stack<GameResult>) {
        internalStorage.write(Json.toJson(gameHistory), "game_history")
    }
}