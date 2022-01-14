package com.s.athrow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.s.athrow.R
import com.s.athrow.activity.MainActivity
import com.s.athrow.adapter.InformationAdapter
import com.s.athrow.databinding.FragmentNewsBinding
import com.s.athrow.network.NetworkService
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi

class NewsDiskFragment : Fragment(R.layout.fragment_news)  {
    private lateinit var binding: FragmentNewsBinding
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        binding.progressBar.visibility = View.GONE
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter =
            InformationAdapter(listOf()) {}
        binding.swipeRefreshLayout.isRefreshing = false
        Snackbar.make(
            requireView(),
            R.string.error,
            Snackbar.LENGTH_SHORT
        ).show()
    }
    private val scope =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + coroutineExceptionHandler)

    companion object {
        fun newInstance() = NewsDiskFragment()
    }

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        loadDisk()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            loadDisk()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
    @ExperimentalSerializationApi
    private fun loadDisk() {
        scope.launch {
            val disk = NetworkService.loadDisk()
            binding.rv.layoutManager = LinearLayoutManager(context)
            binding.rv.adapter =
                InformationAdapter(disk) { (name, image, description) ->
                    (activity as MainActivity).navigateToFragment(
                        DetailsFragment.newInstance(name, description, image)
                    )
                }
            binding.progressBar.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}
