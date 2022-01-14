package com.s.athrow.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.s.athrow.R
import com.s.athrow.data.model.Information

typealias OnInformationClickListener = (Information) -> Unit

class InformationAdapter(
    private val Information: List<Information>,
    private val listener: OnInformationClickListener,
) : RecyclerView.Adapter<InformationAdapter.InformationVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return InformationVH(layoutInflater.inflate(R.layout.item_news1, parent, false), listener)
    }

    override fun onBindViewHolder(holder: InformationVH, position: Int) =
        holder.bind(Information[position])

    override fun getItemCount(): Int = Information.size

    class InformationVH(view: View, listener: OnInformationClickListener) :
        RecyclerView.ViewHolder(view) {


        private val title = view.findViewById<TextView>(R.id.textView1)
        private val description = view.findViewById<TextView>(R.id.textView)
        private val image = view.findViewById<ImageView>(R.id.imageView5)

        private lateinit var Information: Information

        init {
            view.setOnClickListener { listener(Information) }
        }

        fun bind(Information: Information) {
            this.Information = Information
            title.text = Information.title
            description.text = Information.description
            Glide.with(itemView.context)
                .load(Information.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)


        }
    }
}