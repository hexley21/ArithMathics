package com.hxl.domain.repository

import com.hxl.domain.models.GameResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface GameHistoryRepository {

    fun readGameHistory(): Single<List<GameResult>>

    fun insertGameResult(gameHistory: GameResult): Completable

}