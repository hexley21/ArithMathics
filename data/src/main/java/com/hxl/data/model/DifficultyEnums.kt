package com.hxl.data.model

import com.hxl.domain.models.Difficulty


enum class DifficultyEnums(
    val difficulty: Difficulty
) {
    EASY(Difficulty(5, 3, 1..10, "+-", 120)),
    MEDIUM(Difficulty(5, 5, 1..30, "+++---*", 480)),
    HARD(Difficulty(5, 5, 1..100, "+++---*/", 900)),
    CUSTOM(Difficulty(1, 1, 1..20, "+-*/", 60));
}