package com.s.athrow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.athrow.R
import com.s.athrow.activity.MainActivity
import com.s.athrow.adapter.InformationAdapter
import com.s.athrow.data.DataSource.information2
import com.s.athrow.databinding.FragmentNewsBinding

class NewsDiskFragment : Fragment(R.layout.fragment_news)  {
    private lateinit var binding: FragmentNewsBinding

    companion object {
        fun newInstance() = NewsDiskFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)


        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter =
            InformationAdapter(information2) { (name, image, description) ->
                (activity as MainActivity).navigateToFragment(
                    DetailsFragment.newInstance(name, description, image)
                )
            }
    }
}
