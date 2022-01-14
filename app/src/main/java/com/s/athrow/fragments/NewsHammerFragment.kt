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
import com.s.athrow.ScreenState
import com.s.athrow.activity.MainActivity
import com.s.athrow.adapter.InformationAdapter
import com.s.athrow.databinding.FragmentNewsBinding
import com.s.athrow.model.Information
import com.s.athrow.network.NetworkService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi

class NewsHammerFragment  : Fragment(R.layout.fragment_news) {
    private lateinit var binding: FragmentNewsBinding

    companion object {
        fun newInstance() = NewsHammerFragment()
    }


    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        merge(
            flowOf(Unit),
            binding.swipeRefreshLayout.onRefreshFlow(),
            binding.buttonRefresh.onClickFlow()
        ).flatMapLatest { loadHammer() }
            .distinctUntilChanged()
            .onEach {
                when (it) {
                    is ScreenState.DataLoaded -> {
                        setLoading(false)
                        setError(null)
                        setData(it.news)
                    }
                    is ScreenState.Error -> {
                        setLoading(false)
                        setError(it.error)
                        setData(null)
                    }
                    is ScreenState.Loading -> {
                        setLoading(true)
                        setError(null)
                    }
                }
            }.launchIn(lifecycleScope)
    }
    @ExperimentalSerializationApi
    private fun loadHammer() = flow {
        emit(ScreenState.Loading)
        val hammer = NetworkService.loadHammer()
        emit(ScreenState.DataLoaded(hammer))
    }.catch {
        emit(ScreenState.Error(getString(R.string.error)))
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rv.isVisible
        swipeRefreshLayout.isRefreshing = isLoading && rv.isVisible
    }

    private fun setData(news: List<Information>?) = with(binding) {
        swipeRefreshLayout.isVisible = news != null
        binding.rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = InformationAdapter(
            news ?: emptyList()
        ) { (name, image, description) ->
            (activity as MainActivity).navigateToFragment(
                DetailsFragment.newInstance(name, image, description)
            )
        }
    }

    private fun setError(message: String?) = with(binding) {
        errorLayout.isVisible = message != null
        tvError.text = message
    }
}
