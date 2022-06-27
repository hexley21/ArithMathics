package com.hxl.arithmagame.presentation.fragment

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.prefs.GetMode
import com.hxl.domain.usecase.prefs.SaveMode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuFragmentViewModel @Inject constructor(
    private val getMode: GetMode,
    private val saveMode: SaveMode
) : ViewModel() {
    var mode
        get() = getMode.invoke()
        set(value) = saveMode.invoke(value)
}