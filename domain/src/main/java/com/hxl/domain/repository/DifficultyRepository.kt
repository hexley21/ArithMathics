package com.hxl.domain.repository

import com.hxl.domain.models.QuestionDifficulty

interface DifficultyRepository {
    fun getCustom(): QuestionDifficulty

    fun insertCustom(questionDifficulty: QuestionDifficulty)
}