package com.hxl.domain.models

data class GameResult(
    val difficulty: Int,
    val levels: Int,
    val corrects: Int,
    val time: Int
    )