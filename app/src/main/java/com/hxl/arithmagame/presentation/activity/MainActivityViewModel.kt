package com.hxl.arithmagame.presentation.activity

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.QuestionDifficulty
import com.hxl.domain.models.GameResult
import com.hxl.domain.usecase.game_history.GetGameHistory
import com.hxl.domain.usecase.game_history.SaveGameHistory
import com.hxl.domain.usecase.prefs.GetCustom
import com.hxl.domain.usecase.prefs.GetWelcome
import com.hxl.domain.usecase.prefs.SaveCustom
import com.hxl.domain.usecase.prefs.SaveWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getWelcome: GetWelcome,
    private val saveWelcome: SaveWelcome,
    private val getCustom: GetCustom,
    private val saveCustom: SaveCustom,
    private val getGameHistory: GetGameHistory,
    private val saveGameHistory: SaveGameHistory
) : ViewModel(){
    var welcome
        get() = getWelcome()
        set(value) = saveWelcome(value)

    var custom: QuestionDifficulty
        get() = getCustom()
        set(value) = saveCustom(value)

    var gameHistory: Stack<GameResult>
        get() = getGameHistory()
        set(value) = saveGameHistory(value)
}