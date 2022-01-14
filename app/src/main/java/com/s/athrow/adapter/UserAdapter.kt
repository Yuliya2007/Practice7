package com.s.athrow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.s.athrow.R
import com.s.athrow.model.User

typealias OnUserClickListener = (User) -> Unit

class UserAdapter(
    private val user: List<User>,
    private val listener: OnUserClickListener,
) : RecyclerView.Adapter<UserAdapter.UserVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserVH(layoutInflater.inflate(R.layout.item_user, parent, false), listener)
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) =
        holder.bind(user[position])

    override fun getItemCount(): Int = user.size

    class UserVH(view: View, listener: OnUserClickListener) :
        RecyclerView.ViewHolder(view) {

        private val title = view.findViewById<TextView>(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.imageProfile)

        private lateinit var user: User

        init {
            view.setOnClickListener { listener(user) }
        }

        fun bind(user: User) {
            this.user = user
            title.text = user.title
            image.setImageResource(user.coverResId)
        }
    }
}