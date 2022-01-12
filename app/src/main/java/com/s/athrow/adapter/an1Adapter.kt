package com.s.athrow.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.s.athrow.R
import com.s.athrow.model.news

typealias OnnewsClickListener = (news)->Unit

class an1Adapter(
    private val news : List<news>,
    private val listener: OnnewsClickListener,
):RecyclerView.Adapter<an1Adapter.newsVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return newsVH(layoutInflater.inflate(R.layout.item_news, parent, false), listener)
    }

    override fun onBindViewHolder(holder: newsVH, position: Int) = holder.bind(news[position])
    override fun getItemCount(): Int = news.size

    class newsVH(view: View, listener: OnnewsClickListener): RecyclerView.ViewHolder(view) {

        private val iconnews = view.findViewById<ImageView>(R.id.image)
        private val namenews = view.findViewById<TextView>(R.id.textView)
        private val compositionnews = view.findViewById<TextView>(R.id.textView)

        private lateinit var news: news

        init {
            view.setOnClickListener { listener(news) }
        }

        fun bind(news: news) {
            this.news = news
            namenews.text = news.title
            compositionnews.text = news.description
            iconnews.setImageResource(news.coverResId)
        }
    }}