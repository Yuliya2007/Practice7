package com.s.athrow

import com.s.athrow.model.Information

sealed class ScreenState {
    data class DataLoaded(val news: List<Information>) : ScreenState()
    object Loading : ScreenState()
    data class Error(val error: String) : ScreenState()
}
