package com.s.athrow.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitrm.onClickFlow
import com.example.fitrm.onRefreshFlow
import com.s.athrow.R
import com.s.athrow.ScreenState2
import com.s.athrow.adapter.SportsmenAdapter
import com.s.athrow.databinding.FragmentSportsmensBinding
import com.s.athrow.model.Sportsmen
import com.s.athrow.network.NetworkService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi

class SportsmensFragment : Fragment(R.layout.fragment_sportsmens) {
    companion object {
        fun newInstance() = SportsmensFragment()
    }

    private lateinit var binding: FragmentSportsmensBinding
    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSportsmensBinding.bind(view)
        merge(
            flowOf(Unit),
            binding.swipeRefreshLayout.onRefreshFlow(),
            binding.buttonRefresh.onClickFlow()
        ).flatMapLatest { loadSportsmen() }
            .distinctUntilChanged()
            .onEach {
                when (it) {
                    is ScreenState2.DataLoaded -> {
                        setLoading(false)
                        setError(null)
                        setData(it.sportsmen)
                    }
                    is ScreenState2.Error -> {
                        setLoading(false)
                        setError(it.error)
                        setData(null)
                    }
                    is ScreenState2.Loading -> {
                        setLoading(true)
                        setError(null)
                    }
                }
            }.launchIn(lifecycleScope)
    }
    @ExperimentalSerializationApi
    private fun loadSportsmen() = flow {
        emit(ScreenState2.Loading)
        val sportsmen = NetworkService.loadSportsmens()
        emit(ScreenState2.DataLoaded(sportsmen))
    }.catch {
        emit(ScreenState2.Error(getString(R.string.error)))
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rv2.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && rv2.isVisible
    }

    private fun setData(sportsmen: List<Sportsmen>?) = with(binding) {
        swipeRefreshLayout.isVisible = sportsmen != null
        binding.rv2.layoutManager = LinearLayoutManager(context)
        rv2.adapter = SportsmenAdapter(
            sportsmen ?: emptyList()
        ) { (name, image) ->

        }
    }

    private fun setError(message: String?) = with(binding) {
        errorLayout.isVisible = message != null
        tvError.text = message
    }
}