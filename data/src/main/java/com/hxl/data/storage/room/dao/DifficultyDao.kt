package com.hxl.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hxl.data.model.DifficultyDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface DifficultyDao {

    @Query("SELECT * FROM difficulty WHERE id = 1")
    fun getDifficulty(): Single<DifficultyDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDifficulty(difficultyDto: DifficultyDto): Completable

}