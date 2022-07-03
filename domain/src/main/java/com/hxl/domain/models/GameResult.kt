package com.hxl.domain.models

class GameResult(val difficulty: Int, val levels: Int, val corrects: Int){
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
    }
}