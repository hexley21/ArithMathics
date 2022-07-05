package com.hxl.domain.usecase.questions

import com.hxl.domain.models.QuestionDifficulty


enum class DifficultyEnums(
    val questionDifficulty: QuestionDifficulty
) {
    EASY(QuestionDifficulty(5, 3, 1..10, arrayOf("+", "-"), 120)),
    MEDIUM(QuestionDifficulty(5, 5, 1..30, arrayOf("+", "-", "*"), 480)),
    HARD(QuestionDifficulty(5, 5, 1..100, arrayOf("+", "-", "*", "/"), 900)),
    CUSTOM(QuestionDifficulty(1, 1, 1..1, arrayOf("+", "-", "*", "/"), 60));
}