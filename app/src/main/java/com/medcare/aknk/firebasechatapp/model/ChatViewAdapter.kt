//package com.medcare.aknk.firebasechatapp.model
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ListAdapter
//import android.widget.TextView
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//
//
//class ChatViewAdapter(private val chatDataSet: Array<String>):
//    ListAdapter<ChatViewAdapter.ChatViewHolder>(ChatMessageDiffCallBack()) {
//
//
//    override fun onBindViewHolder(chatViewHolder: ChatViewHolder, position: Int) {
//        getItem(position)
//    }
//
//    private fun createOnClickListener(messageId: String): View.OnClickListener {
//        return View.OnClickListener {
////            val direction =
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return chatDataSet.size
//    }
//
//    override fun onCreateViewHolder(chatParentViewGroup: ViewGroup, viewType: Int): ChatViewHolder {
//        val chatTextView = LayoutInflater.from(chatParentViewGroup.context).inflate(com.medcare.aknk.firebasechatapp.R.layout.chat_text_card, chatParentViewGroup, false) as TextView
//        return ChatViewHolder(chatTextView)
//    }
//
//
//    class ChatViewHolder(private val binding: TextView)
//    : RecyclerView.ViewHolder(binding.root) {
//        fun bind(listener: View.OnClickListener, messages: ChatMessage) {
//            with(binding) {
//                clickListener = listener
//                viewModel = ChatMessage(
//                itemView.context,
//                messages
//                )
//                executePendingBindongs()
//            }
//        }
//    }
//}
//
//private class ChatMessageDiffCallBack: DiffUtil.ItemCallback<ChatMessage>() {
//    override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
////        return oldItem.message.messageId == newItem.message.messageId
//    }
//
//    override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
//        return oldItem.message == newItem.message
//    }
//
//}