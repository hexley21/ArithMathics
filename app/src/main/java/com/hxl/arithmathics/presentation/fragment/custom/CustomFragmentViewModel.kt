package com.hxl.arithmathics.presentation.fragment.custom

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.database.difficulty.ReadDifficulty
import com.hxl.domain.usecase.prefs.GetTimer
import com.hxl.domain.usecase.database.difficulty.InsertDifficulty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomFragmentViewModel @Inject constructor(
    val readDifficulty: ReadDifficulty,
    val insertDifficulty: InsertDifficulty,
    val getTimer: GetTimer
) : ViewModel()