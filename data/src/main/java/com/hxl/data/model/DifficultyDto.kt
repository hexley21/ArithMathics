package com.hxl.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hxl.domain.models.Difficulty


@Entity(tableName = "difficulty")
data class DifficultyDto(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "levels") val levels: Int,
    @ColumnInfo(name = "operations") val operations: Int,
    @ColumnInfo(name = "first") val first: Int,
    @ColumnInfo(name = "last") val last: Int,
    @ColumnInfo(name = "operators") val operators: String,
    @ColumnInfo(name = "time") val time: Int
){
    companion object {
        @JvmStatic
        fun toDifficulty(difficulty: DifficultyDto): Difficulty {
            return Difficulty(
                difficulty.levels,
                difficulty.operations,
                difficulty.first..difficulty.last,
                difficulty.operators,
                difficulty.time
            )
        }
    }
}