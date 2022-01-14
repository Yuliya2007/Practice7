package com.s.athrow

import com.s.athrow.model.Sportsmen

sealed class ScreenState2 {
    data class DataLoaded(val sportsmen: List<Sportsmen>) : ScreenState2()
    object Loading : ScreenState2()
    data class Error(val error: String) : ScreenState2()
}
