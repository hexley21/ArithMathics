package com.hxl.domain.repository

import com.hxl.domain.models.QuestionDifficulty

interface CustomRepository {
    fun getCustom(): QuestionDifficulty

    fun insertCustom(questionDifficulty: QuestionDifficulty)
}