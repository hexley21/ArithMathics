package com.hxl.arithmathics.presentation.fragment.welcome

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hxl.data.model.DifficultyEnums
import com.hxl.data.storage.room.LocalDatabase
import com.hxl.domain.usecase.database.difficulty.InsertDifficulty
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetLanguage
import com.hxl.domain.usecase.prefs.SaveLanguage
import com.hxl.domain.usecase.prefs.SaveWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WelcomeFragmentViewModel @Inject constructor(
    val saveWelcome: SaveWelcome,
    private val getLanguage: GetLanguage,
    private val saveLanguage: SaveLanguage,
    private val readDifficulty: ReadDifficulty,
    private val insertDifficulty: InsertDifficulty
) : ViewModel(){
    private val disposable = CompositeDisposable()

    var language
        get() = getLanguage.invoke()
        set(value) = saveLanguage(value)

    // Make sure that database includes at least one record
    fun checkDifficulty() {
        disposable.add(readDifficulty()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { Log.e(LocalDatabase.TAG, "$it was read successfully") },
                { createDifficulty() }
            )
        )
    }

    private fun createDifficulty() {
        disposable.add(insertDifficulty(DifficultyEnums.CUSTOM.difficulty)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { Log.e(LocalDatabase.TAG, "First record created") },
                { Log.e(LocalDatabase.TAG, "Couldn't create first record", it) }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}