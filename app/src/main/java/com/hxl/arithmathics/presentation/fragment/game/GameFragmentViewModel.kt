package com.hxl.arithmathics.presentation.fragment.game

import androidx.lifecycle.ViewModel
import com.hxl.arithmathics.presentation.fragment.game_history.GameResultFormatter
import com.hxl.domain.models.Question
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.prefs.GetTimer
import com.hxl.domain.usecase.questions.GetQuestion
import com.hxl.data.model.DifficultyEnums
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameFragmentViewModel @Inject constructor(
    private val getQuestion: GetQuestion,
    getMode: GetMode,
    readDifficulty: ReadDifficulty,
    val getTimer: GetTimer
) : ViewModel() {

    private val questionEnum = when (getMode()) {
        0 -> DifficultyEnums.EASY.difficulty
        1 -> DifficultyEnums.MEDIUM.difficulty
        2 -> DifficultyEnums.HARD.difficulty
        else -> readDifficulty()
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