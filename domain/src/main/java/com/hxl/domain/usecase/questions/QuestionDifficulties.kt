package com.hxl.domain.usecase.questions

import com.hxl.domain.models.QuestionDifficulty


enum class QuestionDifficulties(
    val levels: Int,
    val operations: Int,
    val range: IntRange,
    val operators: Array<String>,
    val time: Int
) {
    EASY(5, 3, 1..10, arrayOf("+", "-"), 120),
    MEDIUM(5, 5, 1..30, arrayOf("+", "-", "*"), 480),
    HARD(5, 5, 1..100, arrayOf("+", "-", "*", "/"), 900),
    CUSTOM(5, 5, 1..100, arrayOf("+", "-", "*", "/"), 900);

    fun toDifficulty(): QuestionDifficulty {
        return QuestionDifficulty(levels, operations, range, operators, time)
    }
}