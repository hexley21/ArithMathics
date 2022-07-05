package com.hxl.arithmagame.presentation.fragment.menu

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.prefs.GetTimer
import com.hxl.domain.usecase.prefs.SaveMode
import com.hxl.domain.usecase.prefs.SaveTimer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuFragmentViewModel @Inject constructor(
    private val getMode: GetMode,
    private val saveMode: SaveMode,
    private val getTimer: GetTimer,
    private val saveTimer: SaveTimer
) : ViewModel() {
    var mode
        get() = getMode()
        set(value) = saveMode(value)

    var timer
        get() = getTimer()
        set(value) = saveTimer(value)
}