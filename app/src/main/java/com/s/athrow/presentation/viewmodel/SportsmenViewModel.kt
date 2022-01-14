package com.s.athrow.presentation.viewmodel

import android.content.Context
import com.s.athrow.R
import com.s.athrow.domain.network.NetworkService
import com.s.athrow.presentation.ScreenState2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class SportsmenViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState = MutableStateFlow<ScreenState2>(ScreenState2.Loading)
    val screenState: StateFlow<ScreenState2> = _screenState

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.emit(ScreenState2.Loading)
                val sport = NetworkService.loadSportsmens()
                _screenState.emit(ScreenState2.DataLoaded(sport))
            } catch (ex: Throwable) {
                _screenState.emit(ScreenState2.Error(context.resources.getString(R.string.error)))
            }
        }
    }
}