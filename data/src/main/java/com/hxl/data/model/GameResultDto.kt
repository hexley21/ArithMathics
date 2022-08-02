package com.hxl.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hxl.domain.models.GameResult

/**
 * Game-Result Value Object with date as a primary-key.
 * objects will be sorted and filtered by date in future releases.
 */
@Entity(tableName = "game_history")
data class GameResultDto(
    @PrimaryKey
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "difficulty") val difficulty: Int,
    @ColumnInfo(name = "levels") val levels: Int,
    @ColumnInfo(name = "corrects") val corrects: Int,
    @ColumnInfo(name = "time") val time: Int
) {
    companion object {
        @JvmStatic
        fun toGameResult(gameResult: GameResultDto): GameResult {
            return GameResult(
                gameResult.difficulty,
                gameResult.levels,
                gameResult.corrects,
                gameResult.time
            )
        }
    }
}