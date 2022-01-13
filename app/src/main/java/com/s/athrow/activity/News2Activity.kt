
package com.s.athrow.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.s.athrow.databinding.ActivityNews2Binding

class News2Activity  : AppCompatActivity() {
    private lateinit var binding: ActivityNews2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNews2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}