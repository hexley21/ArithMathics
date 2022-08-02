package com.hxl.arithmathics.presentation.fragment.menu

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.prefs.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuFragmentViewModel @Inject constructor(
    private val getMode: GetMode,
    private val saveMode: SaveMode,
    private val getTimer: GetTimer,
    private val saveTimer: SaveTimer,
    private val getPositive: GetPositive,
    private val savePositive: SavePositive
) : ViewModel() {
    var mode: Int
        get() = getMode()
        set(value) = saveMode(value)

    var timer: Boolean
        get() = getTimer()
        set(value) = saveTimer(value)

    var positive: Boolean
        get() = getPositive()
        set(value) = savePositive(value)
}