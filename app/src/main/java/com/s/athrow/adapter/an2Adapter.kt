package com.s.athrow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.s.athrow.R
import com.s.athrow.model.Information

typealias OnInformationClickListener = (Information)->Unit

class an2Adapter(
    private val Information : List<an2Adapter>,
    private val listener: OnInformationClickListener,
):RecyclerView.Adapter<an2Adapter.InformationVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return InformationVH(layoutInflater.inflate(R.layout.item_news1, parent, false), listener)
    }

    override fun onBindViewHolder(holder: InformationVH, position: Int) = holder.bind(Information[position])
    override fun getItemCount(): Int = Information.size

    class InformationVH(view: View, listener: OnsearchClickListener): RecyclerView.ViewHolder(view) {


        private val nameInformation = view.findViewById<TextView>(R.id.textView)
        private val textInformation = view.findViewById<TextView>(R.id.textView)
        private val dateInformation = view.findViewById<TextView>(R.id.textView)
        private val authorName = view.findViewById<TextView>(R.id.textView)

        private lateinit var Information: Information

        init {
            view.setOnClickListener { listener(Information) }
        }

        fun bind(Information: Information) {
            this.Information = Information
            nameInformation.text = Information.title
            nameInformation.text = Information.description

        }
    }}