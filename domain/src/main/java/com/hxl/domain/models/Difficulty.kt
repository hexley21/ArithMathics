package com.hxl.domain.models

data class Difficulty(
    val levels: Int,
    val operations: Int,
    val numberRange: IntRange,
    val operators: String,
    val time: Int) {
    override fun toString(): String {
        return "Difficulty($levels, $operations, $numberRange, $operators, $time)"
    }
}