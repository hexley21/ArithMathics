package com.hxl.arithmagame.presentation.fragment.game

import androidx.lifecycle.ViewModel
import com.hxl.arithmagame.presentation.fragment.game_history.GameResultFormatter
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.prefs.GetCustom
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.questions.GetQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameFragmentViewModel @Inject constructor(
    private val getQuestion: GetQuestion,
    private val getMode: GetMode,
    private val getCustom: GetCustom,
) : ViewModel() {

    val quantity: Int = when (getMode()) {
        1 -> GetQuestion.mediumLevels
        2 -> GetQuestion.hardLevels
        3 -> getCustom().levels
        else -> GetQuestion.easyLevels
    }

    fun generateQuestions(): Array<Question> {
        return when (getMode()) {
            1 -> mediumQuestions()
            2 -> hardQuestions()
            3 -> customQuestion()
            else -> easyQuestions()
        }
    }

    private fun easyQuestions(): Array<Question> {
        return Array(GetQuestion.easyLevels) { getQuestion.easy() }
    }

    private fun mediumQuestions(): Array<Question> {
        return Array(GetQuestion.mediumLevels) { getQuestion.medium() }
    }

    private fun hardQuestions(): Array<Question> {
        return Array(GetQuestion.hardLevels) { getQuestion.hard() }
    }

    private fun customQuestion(): Array<Question> {
        val operations = getCustom().operations
        val numberRange = getCustom().numberRange
        val operators = getCustom().operators
        return Array(getCustom().levels) { getQuestion(operations, numberRange, operators) }
    }

    fun getTimerText(time: Double): String {
        return GameResultFormatter.getTimerText(time)
    }

}