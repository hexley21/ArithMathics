package com.hxl.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hxl.data.model.GameResultDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface GameHistoryDao {
    @Query("SELECT * FROM game_history ORDER BY date DESC")
    fun readGameResult(): Single<List<GameResultDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGameResult(gameResultDto: GameResultDto): Completable

}