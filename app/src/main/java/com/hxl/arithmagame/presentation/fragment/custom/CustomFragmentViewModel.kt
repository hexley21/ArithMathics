package com.hxl.arithmagame.presentation.fragment.custom

import androidx.lifecycle.ViewModel
import com.hxl.domain.models.Custom
import com.hxl.domain.usecase.prefs.GetCustom
import com.hxl.domain.usecase.prefs.SaveCustom
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomFragmentViewModel @Inject constructor(
    private val getCustom: GetCustom,
    private val saveCustom: SaveCustom
) : ViewModel() {
    var custom: Custom
        get() = getCustom()
        set(value) = saveCustom(value)
}