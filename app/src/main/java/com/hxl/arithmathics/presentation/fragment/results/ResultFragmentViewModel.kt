package com.hxl.arithmathics.presentation.fragment.results

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.GameResult
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.database.game_history.ReadGameHistory
import com.hxl.domain.usecase.database.game_history.InsertGameHistory
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.data.model.DifficultyEnums
import com.hxl.domain.models.Difficulty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.log

@HiltViewModel
class ResultFragmentViewModel @Inject constructor(
    private val readGameHistory: ReadGameHistory,
    private val insertGameHistory: InsertGameHistory,
    val getMode: GetMode,
    val readDifficulty: ReadDifficulty
) : ViewModel() {
    lateinit var questions: Array<Question>
    lateinit var answers: Array<String>
    var corrects: Int = 0
    var time: Int = 0

    fun compareAnswers(difficulty: Difficulty): Array<Boolean> {
        val correctArray = Array(questions.size) { questions[it].answer == answers[it] }
        corrects = correctArray.count { it }
        saveGame(difficulty)
        return correctArray
    }

    private fun saveGame(gameMode: Difficulty) {
        val levels = gameMode.levels.toFloat()
        val operations = gameMode.operations
        val range = gameMode.numberRange
        val operators = gameMode.operators.count()
        val stack = readGameHistory()

        val difficulty = floor(log(levels, 100f) * log((range.last - range.first).toFloat(), 2f) * (operators * operations) / 2).toInt()
        stack.push(GameResult(difficulty, questions.size, corrects, time))
        insertGameHistory(stack)
    }
}