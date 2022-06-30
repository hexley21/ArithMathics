package com.hxl.arithmagame.presentation.activity

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.Custom
import com.hxl.domain.usecase.prefs.GetCustom
import com.hxl.domain.usecase.prefs.GetWelcome
import com.hxl.domain.usecase.prefs.SaveCustom
import com.hxl.domain.usecase.prefs.SaveWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getWelcome: GetWelcome,
    private val saveWelcome: SaveWelcome,
    private val getCustom: GetCustom,
    private val saveCustom: SaveCustom
) : ViewModel(){
    var welcome
        get() = getWelcome()
        set(value) = saveWelcome(value)

    var custom: Custom
        get() = getCustom()
        set(value) = saveCustom(value)
}