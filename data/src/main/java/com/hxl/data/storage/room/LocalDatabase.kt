package com.hxl.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hxl.data.model.DifficultyDto
import com.hxl.data.model.GameResultDto
import com.hxl.data.storage.room.dao.DifficultyDao
import com.hxl.data.storage.room.dao.GameHistoryDao

@Database(
    entities = [DifficultyDto::class, GameResultDto::class],
    version = 2,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun difficultyDao(): DifficultyDao

    abstract fun gameHistoryDao(): GameHistoryDao

    companion object {
        const val TAG = "LOCAL_DATABASE"
    }
}