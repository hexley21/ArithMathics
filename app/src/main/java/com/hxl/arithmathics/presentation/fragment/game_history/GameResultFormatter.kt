package com.hxl.arithmathics.presentation.fragment.game_history

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import com.hxl.arithmathics.R

class GameResultFormatter {
    companion object {
        fun getDifficultyColor(difficulty: Int, context: Context): Int {
            val typedValue = TypedValue()
            when (difficulty) {
                in 0..200 -> context.theme.resolveAttribute(com.google.android.material.R.attr.colorTertiaryContainer, typedValue, true)
                in 200..1000 -> context.theme.resolveAttribute(com.google.android.material.R.attr.colorPrimaryContainer, typedValue, true)
                else -> context.theme.resolveAttribute(com.google.android.material.R.attr.colorErrorContainer, typedValue, true)
            }
            return typedValue.data
        }

        fun getDifficultyText(difficulty: Int, resources: Resources): String {
            return when (difficulty) {
                in 0..100 -> resources.getString(R.string.beginner)
                in 100..200 -> resources.getString(R.string.easy)
                in 200..350 -> resources.getString(R.string.medium)
                in 350..1000 -> resources.getString(R.string.hard)
                in 1000..3000 -> resources.getString(R.string.expert)
                in 3000..7000 -> resources.getString(R.string.extreme)
                in 7000..10000 -> resources.getString(R.string.impossible)
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