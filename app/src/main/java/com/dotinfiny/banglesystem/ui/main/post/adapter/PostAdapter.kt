package com.dotinfiny.banglesystem.ui.main.post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dotinfiny.banglesystem.R
import com.dotinfiny.banglesystem.customInterface.OnCustomItemClickListener
import com.dotinfiny.banglesystem.model.api.PostModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(
    val clickListener: OnCustomItemClickListener
) : ListAdapter<PostModel, RecyclerView.ViewHolder>(DiffCallback()) {
    private class DiffCallback : DiffUtil.ItemCallback<PostModel>() {
        override fun areItemsTheSame(
            oldItem: PostModel,
            newItem: PostModel
        ):
                Boolean {
            return oldItem.id.equals(newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: PostModel,
            newItem: PostModel
        ): Boolean {
            return oldItem.id.equals(newItem.id) &&
                    oldItem.body.equals(newItem.body) &&
                    oldItem.userId.equals(newItem.userId) &&
                    oldItem.title.equals(newItem.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tvTitle.text = getItem(position).title
        holder.itemView.tvDesc.text = getItem(position).body
        holder.itemView.action.setOnClickListener {
            clickListener.setOnClickListener(getItem(position), position, holder.itemView.action)
        }
    }


    inner class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer
}