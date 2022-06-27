package com.hxl.arithmagame.presentation.activity

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.prefs.GetTheme
import com.hxl.domain.usecase.prefs.SaveMode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel()