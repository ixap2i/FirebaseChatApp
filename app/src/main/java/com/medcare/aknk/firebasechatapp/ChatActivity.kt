package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.medcare.aknk.firebasechatapp.model.ChatMessage
import com.medcare.aknk.firebasechatapp.model.ChatMessageManager
import com.medcare.aknk.firebasechatapp.model.ChatViewAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ChatActivity : Activity() {

    private lateinit var uid: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var message: String
    private lateinit var image_url: String
    private lateinit var created_at: Date
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private lateinit var db: DatabaseReference

    private lateinit var chatMessageInput: EditText
    private lateinit var chatMessageSendBtn: Button

    private lateinit var recyclerView: RecyclerView

    private var messageList: ArrayList<ChatMessage> = arrayListOf()
    lateinit var adapter: ChatViewAdapter

    fun sendChatMessage(db: DatabaseReference, userId: String, email: String, message: String, imageUrl: String, createdAt: String) {

//        db.addValueEventListener(chatMessageMng.postListener)

        db.child("chat_message").child(userId).setValue(
            mapOf(Pair("message", message),
                Pair("email", email),
                Pair("image_url",image_url),
                Pair("created_at", createdAt)
            )
        ).addOnCompleteListener {
            Log.d("", "write was success")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_base)

//        val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.chat_text_card)


        chatMessageInput = findViewById(R.id.chat_message_input)
        chatMessageSendBtn = findViewById(R.id.chat_message_send_btn)
        recyclerView = findViewById(R.id.chat_recyclerView)
        messageList = arrayListOf()

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = ChatViewAdapter(messageList)


        uid = intent.getStringExtra("uid")
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("password")

        db = FirebaseDatabase.getInstance().getReference("message")


        chatMessageSendBtn.setOnClickListener {
            message = chatMessageInput.text.toString()
            image_url = ""
            created_at = Date()
            val date = dateFormat.format(created_at)


            messageList.add(ChatMessage(uid,
                    message,
                    email,
                    image_url,
                    date))

            sendChatMessage(db, uid, email, message, image_url, date)

            adapter = ChatViewAdapter(messageList)

            Log.d("", "${db}")
            Log.d("", "Chat Activity started successful!")
            Log.d("", "Now user email: ${email}")
            Log.d("", "Now user password: ${password}")

        }

    }

}
