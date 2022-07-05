package com.hxl.arithmagame.presentation.fragment.game

import androidx.lifecycle.ViewModel
import com.hxl.arithmagame.presentation.fragment.game_history.GameResultFormatter
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.prefs.GetCustom
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.prefs.GetTimer
import com.hxl.domain.usecase.questions.GetQuestion
import com.hxl.domain.usecase.questions.QuestionDifficulties
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameFragmentViewModel @Inject constructor(
    private val getQuestion: GetQuestion,
    getMode: GetMode,
    getCustom: GetCustom,
    val getTimer: GetTimer
) : ViewModel() {

    private val questionEnum = when (getMode()) {
        1 -> QuestionDifficulties.MEDIUM
        2 -> QuestionDifficulties.HARD
        else -> QuestionDifficulties.EASY
    }

    val levels = when (getMode()) {
        3 -> getCustom().levels
        else -> questionEnum.levels
    }
    private val operations = when (getMode()) {
        3 -> getCustom().operations
        else -> questionEnum.operations
    }
    private val range = when (getMode()) {
        3 -> getCustom().numberRange
        else -> questionEnum.range
    }
    private val operators = when (getMode()) {
        3 -> getCustom().operators
        else -> questionEnum.operators
    }
    val time = when (getMode()) {
        3 -> getCustom().time
        else -> questionEnum.time
    }

    fun generateQuestions(): Array<Question> {
        return Array(levels) { getQuestion(operations, range, operators) }
    }

    fun getTimerText(time: Int): String {
        return GameResultFormatter.getTimerText(time)
    }

}