package com.hxl.arithmagame.presentation.fragment.game_history

import android.content.res.Resources
import com.hxl.arithmagame.R
import kotlin.math.roundToInt

class GameResultFormatter {
    companion object {
        fun getDifficulty(difficulty: Int, resources: Resources): String {
            return when (difficulty) {
                in 0..2 -> resources.getString(R.string.beginner)
                in 3..11 -> resources.getString(R.string.easy)
                in 12..22 -> resources.getString(R.string.medium)
                in 23..69 -> resources.getString(R.string.hard)
                in 70..149 -> resources.getString(R.string.expert)
                in 150..199 -> resources.getString(R.string.extreme)
                in 200..252 -> resources.getString(R.string.impossible)
                else -> resources.getString(R.string.calculator)
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