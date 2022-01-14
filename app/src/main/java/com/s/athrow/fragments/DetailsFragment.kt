package com.s.athrow.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import coil.load
import com.s.athrow.R
import com.s.athrow.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private lateinit var binding: FragmentDetailsBinding

    companion object {
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        private const val KEY_IMAGE = "KEY_IMAGE"

        fun newInstance(
            name: String,
            image: String,
            description: String,

        ): DetailsFragment {
            val args = bundleOf(
                KEY_NAME to name,
                KEY_DESCRIPTION to description,
                KEY_IMAGE to image,
            )
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        val name = arguments?.getString("KEY_NAME")
        val description = arguments?.getString("KEY_DESCRIPTION")
        val image = arguments?.getString("KEY_IMAGE")

        if (image != null) {
            binding.ivItem.load(image)
        }
        binding.tvTitle.text = name
        binding.tvDescription.text = description
    }
}