package com.daniel.myapp.app_friend.adapter

import com.daniel.myapp.app_friend.model.Friend
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniel.myapp.R
import com.daniel.myapp.app_friend.adapter.RvFriendAdapter.Companion.FriendViewHolder

class FriendAdapter(
    private val context: Context,
    private val onItemClick: (position:Int, data:Friend) -> Unit
) : RecyclerView.Adapter<FriendAdapter.Companion.FriendViewHolder>() {

    companion object {
        class FriendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
             val tvName = view.findViewById<TextView>(R.id.tv_name)
             val tvSchool = view.findViewById<TextView>(R.id.tv_school)
             val ivImage = view.findViewById<ImageView>(R.id.iv_pp)
        }
    }

    private var listData = emptyList<Friend>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val currentItem = listData[position]

        holder.tvName.text = currentItem.name
        holder.tvSchool.text = currentItem.school
        holder.ivImage.setImageDrawable(currentItem.photo)

        holder.itemView.setOnClickListener { onItemClick(position, currentItem) }
    }

    fun setData(list: List<Friend>) {
        this.listData = list
        notifyDataSetChanged()
    }
}