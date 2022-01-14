package com.s.athrow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.s.athrow.R
import com.s.athrow.model.Sportsmen

typealias OnSportsmenClickListener = (Sportsmen) -> Unit

class SportsmenAdapter(
    private val sportsmen: List<Sportsmen>,
    private val listener: OnSportsmenClickListener,
) : RecyclerView.Adapter<SportsmenAdapter.SportsmenVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsmenVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SportsmenVH(layoutInflater.inflate(R.layout.item_sportsmen, parent, false), listener)
    }

    override fun onBindViewHolder(holder: SportsmenVH, position: Int) =
        holder.bind(sportsmen[position])

    override fun getItemCount(): Int = sportsmen.size

    class SportsmenVH(view: View, listener: OnSportsmenClickListener) :
        RecyclerView.ViewHolder(view) {

        private val title = view.findViewById<TextView>(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.imageProfile)

        private lateinit var sportsmen: Sportsmen

        init {
            view.setOnClickListener { listener(sportsmen) }
        }

        fun bind(sportsmen: Sportsmen) {
            this.sportsmen = sportsmen
            title.text = sportsmen.name
            Glide.with(itemView.context)
                .load(sportsmen.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
        }
    }
}