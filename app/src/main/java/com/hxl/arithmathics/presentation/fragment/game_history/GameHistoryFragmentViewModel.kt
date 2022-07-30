package com.hxl.arithmathics.presentation.fragment.game_history

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.database.game_history.ReadGameHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameHistoryFragmentViewModel @Inject constructor(val readGameHistory: ReadGameHistory) : ViewModel()