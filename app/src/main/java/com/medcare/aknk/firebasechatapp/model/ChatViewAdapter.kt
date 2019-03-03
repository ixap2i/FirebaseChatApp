package com.medcare.aknk.firebasechatapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.medcare.aknk.firebasechatapp.R
import com.medcare.aknk.firebasechatapp.databinding.ChatTextCardBinding
import kotlinx.android.synthetic.main.chat_text_card.view.*


class ChatViewAdapter(
        val messageList: ArrayList<ChatMessage>
    ):
    RecyclerView.Adapter<ChatViewAdapter.ChatViewHolder>() {
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.apply {
            bind(messageList)
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

    //    ListAdapter<ChatMessage, ChatViewAdapter.ChatViewHolder>(ChatMessageDiffUtil()) {
//    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
//        val messages = getItem(position)
//        holder.apply {
//            bind(messages)
//        }
//    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
//        return ChatViewAdapter.ChatViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
//            R.layout.chat_text_card,
//            parent, false))
//
//    }

    class ChatViewHolder(private val binding: ChatTextCardBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(messages: ArrayList<ChatMessage>) {
            binding.apply {
                binding.chatTextCard.text = messages[0].chatMessage
//                executePendingBindings()
            }
        }
    }

}

private class  ChatMessageDiffUtil: DiffUtil.ItemCallback<ChatMessage>() {
    override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
//        val equals = oldItem.createdAt == newItem.createdAt
        return true
   }

    override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        return oldItem == newItem
    }

}