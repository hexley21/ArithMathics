package com.hxl.domain.models

data class Difficulty(
    val levels: Int,
    val operations: Int,
    val numberRange: IntRange,
    val operators: String,
    val time: Int)