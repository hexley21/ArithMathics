package com.hxl.arithmagame.presentation.fragment.results

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.GameResult
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.game_history.GetGameHistory
import com.hxl.domain.usecase.game_history.SaveGameHistory
import com.hxl.domain.usecase.prefs.GetCustom
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.questions.GetQuestion
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

    fun compareAnswers(): Array<Boolean> {
        val correctArray = Array(questions.size) { questions[it].answer == answers[it] }
        corrects = correctArray.count { it }
        saveGame()
        return correctArray
    }

    private fun saveGame() {
        val operators = when(getMode()){
            0 -> GetQuestion.easyOperators
            1 -> GetQuestion.mediumOperators
            2 -> GetQuestion.hardOperators
            else -> getCustom().operators.size
        }
        val operations = when(getMode()){
            0 -> GetQuestion.easyOperations
            1 -> GetQuestion.mediumOperations
            2 -> GetQuestion.hardOperations
            else -> getCustom().operations
        }
        val range = when(getMode()){
            0 -> GetQuestion.easyRange
            1 -> GetQuestion.mediumRange
            2 -> GetQuestion.hardRange
            else -> getCustom().numberRange.last - getCustom().numberRange.first
        }
        val stack = getGameHistory()
        val difficulty = floor(log(questions.size.toFloat(), 100f) * log(range.toFloat(), 2f) * (operators * operations) / 2).toInt()
        stack.push(GameResult(difficulty, questions.size, corrects))
        saveGameHistory(stack)
    }
}