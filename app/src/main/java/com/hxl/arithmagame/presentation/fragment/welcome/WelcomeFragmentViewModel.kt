package com.hxl.arithmagame.presentation.fragment.welcome

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.prefs.GetLanguage
import com.hxl.domain.usecase.prefs.GetWelcome
import com.hxl.domain.usecase.prefs.SaveLanguage
import com.hxl.domain.usecase.prefs.SaveWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeFragmentViewModel @Inject constructor(
    val saveWelcome: SaveWelcome,
    private val getLanguage: GetLanguage,
    private val saveLanguage: SaveLanguage
) : ViewModel(){
    var language
        get() = getLanguage.invoke()
        set(value) = saveLanguage(value)
}