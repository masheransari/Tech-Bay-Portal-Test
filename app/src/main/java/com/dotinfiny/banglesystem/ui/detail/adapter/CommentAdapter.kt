package com.dotinfiny.banglesystem.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dotinfiny.banglesystem.R
import com.dotinfiny.banglesystem.customInterface.OnCustomItemClickListener
import com.dotinfiny.banglesystem.model.api.CommentModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(
    val clickListener: OnCustomItemClickListener
) : ListAdapter<CommentModel, RecyclerView.ViewHolder>(DiffCallback()) {
    private class DiffCallback : DiffUtil.ItemCallback<CommentModel>() {
        override fun areItemsTheSame(
            oldItem: CommentModel,
            newItem: CommentModel
        ):
                Boolean {
            return oldItem.id.equals(newItem.id)
        }

        override fun areContentsTheSame(
            oldItem: CommentModel,
            newItem: CommentModel
        ): Boolean {
            return oldItem.id.equals(newItem.id) &&
                    oldItem.body.equals(newItem.body) &&
                    oldItem.email.equals(newItem.email) &&
                    oldItem.name.equals(newItem.name) &&
                    oldItem.postId.equals(newItem.postId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.body.text = getItem(position).body
        holder.itemView.name.text = getItem(position).name
        holder.itemView.email.text = getItem(position).email
    }


    inner class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer
}