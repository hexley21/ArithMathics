package com.hxl.data.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hxl.data.model.DifficultyDto
import com.hxl.data.storage.room.dao.DifficultyDao

@Database(entities = [DifficultyDto::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun difficultyDao(): DifficultyDao

    companion object {

        @Volatile private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                LocalDatabase::class.java, "arithmathics.db")
                .fallbackToDestructiveMigration()
                .build()

        const val TAG = "LOCAL_DATABASE"
    }
}