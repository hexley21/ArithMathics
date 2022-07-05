package com.hxl.arithmagame.presentation.fragment.game_history

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.hxl.arithmagame.R

class GameResultFormatter {
    companion object {
        fun getDifficultyColor(difficulty: Int, context: Context): Int {
            val typedValue = TypedValue()
            when (difficulty) {
                in 23..149 -> context.theme.resolveAttribute(com.google.android.material.R.attr.colorPrimaryContainer, typedValue, true)
                in 150..254 -> context.theme.resolveAttribute(com.google.android.material.R.attr.colorErrorContainer, typedValue, true)
                else -> context.theme.resolveAttribute(com.google.android.material.R.attr.colorTertiaryContainer, typedValue, true)
            }
            return typedValue.data
        }

        fun getDifficultyText(difficulty: Int, resources: Resources): String {
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

        fun getTimerText(time: Int): String {
            val seconds = time % 86400 % 3600 % 60
            val minutes = time / 60
            return String.format("%02d", minutes) + " : " + String.format("%02d", seconds)
        }
    }
}