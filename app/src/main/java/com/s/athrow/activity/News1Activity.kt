package com.s.athrow.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.s.athrow.databinding.ActivityNews1Binding

class News1Activity  : AppCompatActivity() {
    private lateinit var binding: ActivityNews1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNews1Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}