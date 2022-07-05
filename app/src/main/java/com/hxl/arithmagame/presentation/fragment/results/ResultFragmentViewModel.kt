package com.hxl.arithmagame.presentation.fragment.results

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.GameResult
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.game_history.GetGameHistory
import com.hxl.domain.usecase.game_history.SaveGameHistory
import com.hxl.domain.usecase.prefs.GetCustom
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.questions.QuestionDifficulties
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.log

@HiltViewModel
class ResultFragmentViewModel @Inject constructor(
    private val getGameHistory: GetGameHistory,
    private val saveGameHistory: SaveGameHistory,
    private val getMode: GetMode,
    private val getCustom: GetCustom
) : ViewModel() {
    lateinit var questions: Array<Question>
    lateinit var answers: Array<String>
    var corrects: Int = 0
    var time: Int = 0

    fun compareAnswers(): Array<Boolean> {
        val correctArray = Array(questions.size) { questions[it].answer == answers[it] }
        corrects = correctArray.count { it }
        saveGame()
        return correctArray
    }

    private fun saveGame() {
        val questionEnum = when (getMode()) {
            1 -> QuestionDifficulties.MEDIUM
            2 -> QuestionDifficulties.HARD
            else -> QuestionDifficulties.EASY
        }
        val levels = when (getMode()) {
            3 -> getCustom().levels.toFloat()
            else -> questionEnum.levels.toFloat()
        }
        val operations = when (getMode()) {
            3 -> getCustom().operations
            else -> questionEnum.operations
        }
        val range = when (getMode()) {
            3 -> getCustom().numberRange
            else -> questionEnum.range
        }
        val operators = when (getMode()) {
            3 -> getCustom().operators.size
            else -> questionEnum.operators.size
        }
        val stack = getGameHistory()
        val difficulty = floor(log(levels, 100f) * log((range.last - range.first).toFloat(), 2f) * (operators * operations) / 2).toInt()
        stack.push(GameResult(difficulty, questions.size, corrects, time))
        saveGameHistory(stack)
    }
}