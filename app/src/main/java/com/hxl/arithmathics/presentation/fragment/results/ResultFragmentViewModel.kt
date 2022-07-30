package com.hxl.arithmathics.presentation.fragment.results

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.GameResult
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.database.game_history.ReadGameHistory
import com.hxl.domain.usecase.database.game_history.InsertGameHistory
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.data.model.DifficultyEnums
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.log

@HiltViewModel
class ResultFragmentViewModel @Inject constructor(
    private val readGameHistory: ReadGameHistory,
    private val insertGameHistory: InsertGameHistory,
    private val getMode: GetMode,
    private val readDifficulty: ReadDifficulty
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
            0 -> DifficultyEnums.EASY.difficulty
            1 -> DifficultyEnums.MEDIUM.difficulty
            2 -> DifficultyEnums.HARD.difficulty
            else -> readDifficulty()
        }
        val levels = questionEnum.levels.toFloat()
        val operations = questionEnum.operations
        val range = questionEnum.numberRange
        val operators = questionEnum.operators.count()
        val stack = readGameHistory()

        val difficulty = floor(log(levels, 100f) * log((range.last - range.first).toFloat(), 2f) * (operators * operations) / 2).toInt()
        stack.push(GameResult(difficulty, questions.size, corrects, time))
        insertGameHistory(stack)
    }
}