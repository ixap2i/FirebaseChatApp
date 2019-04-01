package com.medcare.aknk.firebasechatapp.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.medcare.aknk.firebasechatapp.R
import com.medcare.aknk.firebasechatapp.databinding.ChatTextCardBinding


class ChatViewAdapter(
        val messageList: ArrayList<ChatMessage>
    ):
    RecyclerView.Adapter<ChatViewAdapter.ChatViewHolder>() {
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.apply {
            bind(messageList, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewAdapter.ChatViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.chat_text_card,
            parent, false))
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class ChatViewHolder(private val binding: ChatTextCardBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(messages: ArrayList<ChatMessage>, position: Int) {
            binding.apply {
                binding.chatTextCard.text = messages[position].chatMessage
            }
        }
    }

}

private class  ChatMessageDiffUtil: DiffUtil.ItemCallback<ChatMessage>() {
    override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        val equals = oldItem.id == newItem.id
        return equals
   }

    override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        return oldItem == newItem
    }

}
