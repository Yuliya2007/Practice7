package com.s.athrow.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.athrow.R
import com.s.athrow.presentation.ScreenState2
import com.s.athrow.presentation.adapter.SportsmenAdapter
import com.s.athrow.databinding.FragmentSportsmensBinding
import com.s.athrow.data.model.Sportsmen
import com.s.athrow.presentation.viewmodel.SportsmenViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi

class SportsmensFragment : Fragment(R.layout.fragment_sportsmens) {
    private val viewModel by lazy { SportsmenViewModel(requireContext(), lifecycleScope) }
    companion object {
        fun newInstance() = SportsmensFragment()
    }

    private lateinit var binding: FragmentSportsmensBinding
    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSportsmensBinding.bind(view)
        if (savedInstanceState == null) {
            viewModel.loadData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.loadData() }
        binding.buttonRefresh.setOnClickListener { viewModel.loadData() }
        viewModel.screenState.onEach {
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