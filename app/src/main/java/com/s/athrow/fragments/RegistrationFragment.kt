package com.s.athrow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s.athrow.R
import com.s.athrow.activity.MainActivity
import com.s.athrow.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    private lateinit var binding: FragmentRegistrationBinding
    companion object {
        fun newInstance() = RegistrationFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)

        binding.btnEnter.setOnClickListener {
            (activity as MainActivity).navigateToFragment(NavigateFragment.newInstance())
        }

    }
}
