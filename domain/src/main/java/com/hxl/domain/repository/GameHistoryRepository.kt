package com.hxl.domain.repository

import com.hxl.domain.models.GameResult
import java.util.*

interface GameHistoryRepository {

    fun getGameHistory(): Stack<GameResult>

    fun saveGameResult(gameHistory: Stack<GameResult>)

}