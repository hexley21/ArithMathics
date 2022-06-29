package com.hxl.arithmagame.presentation.activity

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.prefs.GetWelcome
import com.hxl.domain.usecase.prefs.SaveWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getWelcome: GetWelcome,
    private val saveWelcome: SaveWelcome
) : ViewModel(){
    var welcome
        get() = getWelcome.invoke()
        set(value) = saveWelcome(value)
}