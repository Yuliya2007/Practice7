package com.s.athrow.presentation

import com.s.athrow.data.model.Information

sealed class ScreenState {
    data class DataLoaded(val news: List<Information>) : ScreenState()
    object Loading : ScreenState()
    data class Error(val error: String) : ScreenState()
}
