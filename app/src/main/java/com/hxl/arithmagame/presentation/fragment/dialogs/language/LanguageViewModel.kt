package com.hxl.arithmagame.presentation.fragment.dialogs.language

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.prefs.GetLanguage
import com.hxl.domain.usecase.prefs.GetTheme
import com.hxl.domain.usecase.prefs.SaveLanguage
import com.hxl.domain.usecase.prefs.SaveTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val getLanguage: GetLanguage,
    private val saveLanguage: SaveLanguage
) : ViewModel() {
    var language
        get() = getLanguage.invoke()
        set(value) = saveLanguage(value)
}