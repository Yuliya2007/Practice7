package com.s.athrow.presentation.viewmodel

import android.content.Context
import com.s.athrow.R
import com.s.athrow.data.database.DatabaseProvider
import com.s.athrow.domain.network.NetworkService
import com.s.athrow.presentation.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.IOException

class SpearViewModel(
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screenState
    private val dao = DatabaseProvider.provideDatabase(context).sportDao()

    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.value = ScreenState.Loading
                val news = try {
                    NetworkService(context).loadSpear().also {
                        dao.insertAll(it)
                    }
                } catch (ex: IOException){
                    dao.getAll()
                }
                _screenState.value = ScreenState.DataLoaded(news)
            } catch(ex: Throwable) {
                _screenState.value = ScreenState.Error(context.getString(R.string.error))
            }
        }
    }
}