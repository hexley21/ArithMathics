package com.hxl.arithmathics.presentation.activity

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.Difficulty
import com.hxl.domain.models.GameResult
import com.hxl.domain.usecase.database.game_history.ReadGameHistory
import com.hxl.domain.usecase.database.game_history.InsertGameHistory
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetWelcome
import com.hxl.domain.usecase.database.difficulty.InsertDifficulty
import com.hxl.domain.usecase.prefs.SaveWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getWelcome: GetWelcome,
    private val saveWelcome: SaveWelcome,
    private val readDifficulty: ReadDifficulty,
    private val insertDifficulty: InsertDifficulty,
    private val readGameHistory: ReadGameHistory,
    private val insertGameHistory: InsertGameHistory
) : ViewModel(){
    var welcome
        get() = getWelcome()
        set(value) = saveWelcome(value)

    var difficulty: Difficulty
        get() = readDifficulty()
        set(value) = insertDifficulty(value)

    var gameHistory: Stack<GameResult>
        get() = readGameHistory()
        set(value) = insertGameHistory(value)
}