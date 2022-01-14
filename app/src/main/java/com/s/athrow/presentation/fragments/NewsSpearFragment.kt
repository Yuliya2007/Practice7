package com.s.athrow.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.athrow.R
import com.s.athrow.presentation.ScreenState
import com.s.athrow.presentation.activity.MainActivity
import com.s.athrow.databinding.FragmentNewsBinding
import com.s.athrow.data.model.Information
import com.s.athrow.presentation.adapter.InformationAdapter
import com.s.athrow.presentation.viewmodel.SpearViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi

class NewsSpearFragment : Fragment(R.layout.fragment_news) {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel by lazy { SpearViewModel(requireContext(), lifecycleScope) }

    companion object {
        fun newInstance() = NewsSpearFragment()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        if (savedInstanceState == null) {
            viewModel.loadData()
        }
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.loadData() }
        binding.buttonRefresh.setOnClickListener { viewModel.loadData() }
        viewModel.screenState.onEach {
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