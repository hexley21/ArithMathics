package com.hxl.domain.repository

import com.hxl.domain.models.Difficulty

interface DifficultyRepository {
    fun readCustom(): Difficulty

    fun insertCustom(difficulty: Difficulty)
}