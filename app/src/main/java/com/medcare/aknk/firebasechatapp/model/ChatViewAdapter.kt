package com.medcare.aknk.firebasechatapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.medcare.aknk.firebasechatapp.databinding.ChatTextCardBinding


class ChatViewAdapter:
    ListAdapter<ChatMessage, ChatViewAdapter.ChatViewHolder>(ChatMessageDiffUtil()) {
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val message = getItem(position)
        holder.apply {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewAdapter.ChatViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            0,
            parent, false))

    }


    class ChatViewHolder(private val binding: ChatTextCardBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: ChatMessage) {
            binding.apply {
                executePendingBindings()
            }
        }
    }

}

private class  ChatMessageDiffUtil: DiffUtil.ItemCallback<ChatMessage>() {
    override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}