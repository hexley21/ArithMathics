package com.hxl.arithmathics.presentation.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hxl.data.model.DifficultyEnums
import com.hxl.data.storage.room.LocalDatabase
import com.hxl.domain.models.GameResult
import com.hxl.domain.usecase.database.game_history.ReadGameHistory
import com.hxl.domain.usecase.database.game_history.InsertGameHistory
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetWelcome
import com.hxl.domain.usecase.database.difficulty.InsertDifficulty
import com.hxl.domain.usecase.prefs.SaveWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getWelcome: GetWelcome,
    private val saveWelcome: SaveWelcome,
    val readDifficulty: ReadDifficulty,
    val insertDifficulty: InsertDifficulty,
    private val readGameHistory: ReadGameHistory,
    private val insertGameHistory: InsertGameHistory
) : ViewModel() {
    private val disposable = CompositeDisposable()

    var welcome
        get() = getWelcome()
        set(value) = saveWelcome(value)

    var gameHistory: Stack<GameResult>
        get() = readGameHistory()
        set(value) = insertGameHistory(value)

    fun checkDifficulty() {
        disposable.add(readDifficulty()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(LocalDatabase.TAG, "Difficulty was read successfully")
                    disposable.clear()
                },
                { createDifficulty() }
            )
        )
    }

    private fun createDifficulty() {
        disposable.add(insertDifficulty(DifficultyEnums.CUSTOM.difficulty)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(LocalDatabase.TAG, "First record created")
                    disposable.clear()
                },
                { Log.e(LocalDatabase.TAG, "Couldn't create first record", it) }
            )
        )
    }

}