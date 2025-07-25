package com.daniel.myapp.app_friend.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniel.myapp.R
import com.daniel.myapp.app_friend.database.entity.FriendEntity
import com.daniel.myapp.app_friend.model.Friend

class RvFriendAdapter(
    private val context: Context,
    private val onItemClick: (position: Int, data: FriendEntity) -> Unit,
    private val onDeleteClick: (position: Int, data: FriendEntity) -> Unit
) : RecyclerView.Adapter<RvFriendAdapter.Companion.FriendViewHolder>() {

    private var listItem = emptyList<FriendEntity>()

    companion object {
        class FriendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tv_name)
            val tvSchool: TextView = view.findViewById(R.id.tv_school)
            val ivDelete: ImageView = view.findViewById(R.id.iv_delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_rv_friend, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val currentItem = listItem[position]

        holder.tvName.text = currentItem.name
        holder.tvSchool.text = currentItem.school
//        holder.ivPhoto.setImageDrawable(currentItem.photo)

        holder.itemView.setOnClickListener { onItemClick(position, currentItem) }
        holder.ivDelete.setOnClickListener { onDeleteClick(position, currentItem) }
    }

    fun setData(list: List<FriendEntity>) {
        this.listItem = list
        notifyDataSetChanged()
    }
}