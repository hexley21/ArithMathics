package com.hxl.arithmathics.presentation.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hxl.data.model.DifficultyEnums
import com.hxl.data.storage.room.LocalDatabase
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetWelcome
import com.hxl.domain.usecase.database.difficulty.InsertDifficulty
import com.hxl.domain.usecase.prefs.SaveWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getWelcome: GetWelcome,
    private val saveWelcome: SaveWelcome,
    val readDifficulty: ReadDifficulty,
    val insertDifficulty: InsertDifficulty,
) : ViewModel() {
    private val disposable = CompositeDisposable()

    var welcome
        get() = getWelcome()
        set(value) = saveWelcome(value)

    fun checkDifficulty() {
        disposable.add(readDifficulty()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.e(LocalDatabase.TAG, "$it was read successfully")
                    disposable.clear()
                },
                { createDifficulty() }
            )
        )
    }

    private fun createDifficulty() {
        disposable.add(insertDifficulty(DifficultyEnums.CUSTOM.difficulty)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.e(LocalDatabase.TAG, "First record created")
                    disposable.clear()
                },
                { Log.e(LocalDatabase.TAG, "Couldn't create first record", it) }
            )
        )
    }

}