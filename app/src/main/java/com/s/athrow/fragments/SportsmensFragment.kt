package com.s.athrow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.s.athrow.R
import com.s.athrow.adapter.SportsmenAdapter
import com.s.athrow.databinding.FragmentSportsmensBinding
import com.s.athrow.network.NetworkService
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi

class SportsmensFragment : Fragment(R.layout.fragment_sportsmens) {
    companion object {
        fun newInstance() = SportsmensFragment()
    }
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        binding.progressBar.visibility = View.GONE
        binding.rv2.layoutManager = LinearLayoutManager(context)
        binding.rv2.adapter =
            SportsmenAdapter(listOf()) {}
        binding.swipeRefreshLayout.isRefreshing = false
        Snackbar.make(
            requireView(),
            R.string.error,
            Snackbar.LENGTH_SHORT
        ).show()
    }
    private val scope =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + coroutineExceptionHandler)


    private lateinit var binding: FragmentSportsmensBinding
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSportsmensBinding.bind(view)

        loadSportsmen()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            loadSportsmen()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
    @ExperimentalSerializationApi
    private fun loadSportsmen() {
        scope.launch {
            val sportsmen = NetworkService.loadSportsmens()
            binding.rv2.layoutManager = LinearLayoutManager(context)
            binding.rv2.adapter =
                SportsmenAdapter(sportsmen) { (name, image) ->
                }
            binding.progressBar.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}