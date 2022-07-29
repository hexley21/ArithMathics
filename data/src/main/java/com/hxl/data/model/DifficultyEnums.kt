package com.hxl.data.model

import com.hxl.domain.models.QuestionDifficulty


enum class DifficultyEnums(
    val questionDifficulty: QuestionDifficulty
) {
    EASY(QuestionDifficulty(5, 3, 1..10, "+-", 120)),
    MEDIUM(QuestionDifficulty(5, 5, 1..30, "++++----**", 480)),
    HARD(QuestionDifficulty(5, 5, 1..100, "+++---*/", 900)),
    CUSTOM(QuestionDifficulty(1, 1, 1..1, "++++---*", 60));
}