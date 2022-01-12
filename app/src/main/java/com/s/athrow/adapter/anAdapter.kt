package com.s.athrow.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.s.athrow.R
import com.s.athrow.model.search

typealias OnsearchClickListener = (search)->Unit

class anAdapter(
    private val search : List<search>,
    private val listener: OnsearchClickListener,
):RecyclerView.Adapter<anAdapter.searchVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return searchVH(layoutInflater.inflate(R.layout.item_news1, parent, false), listener)
    }

    override fun onBindViewHolder(holder: searchVH, position: Int) = holder.bind(search[position])
    override fun getItemCount(): Int = search.size

    class searchVH(view: View, listener: OnsearchClickListener): RecyclerView.ViewHolder(view) {


        private val namesearch = view.findViewById<TextView>(R.id.image)
        private val textsearch = view.findViewById<TextView>(R.id.textView)
        private val datesearch = view.findViewById<TextView>(R.id.textView)
        private val authorName = view.findViewById<TextView>(R.id.textView)

        private lateinit var search: search

        init {
            view.setOnClickListener { listener(search) }
        }

        fun bind(article: search) {
           this.search = search
            namesearch.text = search.description

        }
    }}
