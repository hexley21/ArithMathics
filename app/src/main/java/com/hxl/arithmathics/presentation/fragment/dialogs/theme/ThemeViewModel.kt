package com.hxl.arithmathics.presentation.fragment.dialogs.theme

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.prefs.GetTheme
import com.hxl.domain.usecase.prefs.SaveTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val getTheme: GetTheme,
    private val saveTheme: SaveTheme
) : ViewModel() {
    var theme
        get() = getTheme.invoke()
        set(value) = saveTheme(value)
}