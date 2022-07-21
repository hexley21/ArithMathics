package com.hxl.arithmathics.presentation.fragment.game_history

import androidx.lifecycle.ViewModel
import com.hxl.domain.usecase.game_history.GetGameHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameHistoryFragmentViewModel @Inject constructor(val getGameHistory: GetGameHistory) : ViewModel()