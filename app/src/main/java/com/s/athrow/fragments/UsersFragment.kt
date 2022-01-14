package com.s.athrow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.s.athrow.R
import com.s.athrow.databinding.FragmentUsersBinding

class UsersFragment  : Fragment(R.layout.fragment_users) {
    companion object {
        fun newInstance() = UsersFragment()
    }
    private lateinit var binding: FragmentUsersBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsersBinding.bind(view)

    }
}