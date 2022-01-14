package com.s.athrow.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.s.athrow.R
import com.s.athrow.databinding.ActivityMainBinding
import com.s.athrow.fragments.RegistrationFragment

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        if (savedInstanceState == null) {
            navigateToFragment(RegistrationFragment.newInstance())
        }
    }

    fun navigateToFragment(fmt: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fmt)
            .addToBackStack(fmt.javaClass.name)
            .commit()
    }
}
