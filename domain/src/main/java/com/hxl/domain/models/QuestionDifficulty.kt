package com.hxl.domain.models

data class QuestionDifficulty(
    val levels: Int,
    val operations: Int,
    val numberRange: IntRange,
    val operators: String,
    val time: Int)