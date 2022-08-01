package com.hxl.arithmathics.presentation.fragment.game

import androidx.lifecycle.ViewModel
import com.hxl.arithmathics.presentation.fragment.game_history.GameResultFormatter
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.prefs.GetTimer
import com.hxl.domain.usecase.questions.GetQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameFragmentViewModel @Inject constructor(
    val getQuestion: GetQuestion,
    val getMode: GetMode,
    val readDifficulty: ReadDifficulty,
    val getTimer: GetTimer
) : ViewModel() {

    fun getTimerText(time: Int): String {
        return GameResultFormatter.getTimerText(time)
    }

}