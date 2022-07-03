package com.hxl.domain.models

import kotlin.math.roundToInt

class GameResult(val difficulty: Int, val levels: Int, val corrects: Int, val time: Double){
    companion object{
        fun getDifficulty(difficulty: Int): String{
            return when(difficulty){
                in 0..2 -> "Beginner"
                in 3..11 -> "Easy"
                in 12..22 -> "Medium"
                in 23..69 -> "Hard"
                in 70..149 -> "Expert"
                in 150..199 -> "Extreme"
                in 200..252 -> "Impossible"
                else -> "Calculator"
            }
        }
        fun getTimerText(time: Double): String {
            val rounded = time.roundToInt()
            val seconds = rounded % 86400 % 3600 % 60
            val minutes = rounded / 60
            return String.format("%02d", minutes) + " : " + String.format("%02d", seconds)
        }
    }
}