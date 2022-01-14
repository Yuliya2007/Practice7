package com.s.athrow.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.s.athrow.databinding.ActivityMainBinding
import com.s.athrow.databinding.ActivityNavigateBinding

class NavigateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hammer.setOnClickListener {
            val intent = Intent(this, NewsHammerActivity::class.java)
            startActivity(intent)
        }
        binding.spear.setOnClickListener {
            val intent = Intent(this, NewsSpearActivity::class.java)
            startActivity(intent)
        }
        binding.disk.setOnClickListener {
            val intent = Intent(this, NewsDiskActivity::class.java)
            startActivity(intent)
        }
    }
}