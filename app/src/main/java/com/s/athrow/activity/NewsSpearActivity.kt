package com.s.athrow.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.athrow.adapter.InformationAdapter
import com.s.athrow.data.DataSource
import com.s.athrow.databinding.ActivityNewsBinding

class NewsSpearActivity  : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter =
            InformationAdapter(DataSource.information3) { (name, description, image) ->
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra(NewsDiskActivity.KEY_NAME, name)
                intent.putExtra(NewsDiskActivity.KEY_DESCRIPTION, description)
                intent.putExtra(NewsDiskActivity.KEY_ICON_RES_ID, image)
                startActivity(intent)
            }
    }
}
