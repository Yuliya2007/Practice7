package com.s.athrow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s.athrow.R
import com.s.athrow.activity.MainActivity
import com.s.athrow.databinding.FragmentNavigateBinding

class NavigateFragment : Fragment(R.layout.fragment_navigate)  {
    private lateinit var binding: FragmentNavigateBinding

    companion object {
        fun newInstance() = NavigateFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNavigateBinding.bind(view)

        binding.hammer.setOnClickListener {
            (activity as MainActivity).navigateToFragment(NewsHammerFragment.newInstance())
        }
        binding.spear.setOnClickListener {
            (activity as MainActivity).navigateToFragment(NewsSpearFragment.newInstance())
        }
        binding.disk.setOnClickListener {
            (activity as MainActivity).navigateToFragment(NewsDiskFragment.newInstance())
        }
    }
}