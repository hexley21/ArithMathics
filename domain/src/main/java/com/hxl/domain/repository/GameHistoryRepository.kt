package com.hxl.domain.repository

import com.hxl.domain.models.GameResult
import java.util.*

interface GameHistoryRepository {

    fun readGameHistory(): Stack<GameResult>

    fun insertGameResult(gameHistory: Stack<GameResult>)

}