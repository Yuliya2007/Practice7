package com.s.athrow.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.s.athrow.adapter.InformationAdapter
import com.s.athrow.data.DataSource.information2
import com.s.athrow.databinding.ActivityNewsBinding

class NewsDiskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    companion object {
        const val KEY_NAME = "name"
        const val KEY_DESCRIPTION = "description"
        const val KEY_ICON_RES_ID = "iconResId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter =
            InformationAdapter(information2) { (name, description, image) ->
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra(KEY_NAME, name)
                intent.putExtra(KEY_DESCRIPTION, description)
                intent.putExtra(KEY_ICON_RES_ID, image)
                startActivity(intent)
            }
    }
}
