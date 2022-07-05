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
        0 -> QuestionDifficulties.EASY.toDifficulty()
        1 -> QuestionDifficulties.MEDIUM.toDifficulty()
        2 -> QuestionDifficulties.HARD.toDifficulty()
        else -> getCustom()
    }

    val levels = questionEnum.levels
    private val operations = questionEnum.operations
    private val range = questionEnum.numberRange
    private val operators = questionEnum.operators
    val time = questionEnum.time

    fun generateQuestions(): Array<Question> {
        return Array(levels) { getQuestion(operations, range, operators) }
    }

    fun getTimerText(time: Int): String {
        return GameResultFormatter.getTimerText(time)
    }

}