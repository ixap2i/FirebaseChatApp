package com.medcare.aknk.firebasechatapp.model

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.medcare.aknk.firebasechatapp.R

class ChatViewAdapter(private val chatDataSet: Array<String>):
    RecyclerView.Adapter<ChatViewAdapter.ChatViewHolder>() {
    override fun onBindViewHolder(chatViewHolder: ChatViewHolder, position: Int) {
        chatViewHolder.componentView.text = chatDataSet[position]

    }

    override fun getItemCount(): Int {
        return chatDataSet.size
    }

    override fun onCreateViewHolder(chatParentViewGroup: ViewGroup, viewType: Int): ChatViewHolder {
        val chatTextView = LayoutInflater.from(chatParentViewGroup.context).inflate(R.layout.chat_text_card, chatParentViewGroup, false) as TextView
        return ChatViewHolder(chatTextView)
    }


    class ChatViewHolder(val componentView: TextView): RecyclerView.ViewHolder(componentView)
}