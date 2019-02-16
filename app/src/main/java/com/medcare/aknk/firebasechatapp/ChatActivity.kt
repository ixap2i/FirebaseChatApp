package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.BinderThread
import androidx.constraintlayout.widget.Constraints.TAG
import androidx.room.Database
import com.google.firebase.database.*
import com.medcare.aknk.firebasechatapp.model.ChatMessage
import com.medcare.aknk.firebasechatapp.model.ChatMessageManager

import kotlinx.android.synthetic.main.activity_chat_base.*
import org.json.JSONObject

class ChatActivity : Activity() {

    private lateinit var uid: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var message: String
    private lateinit var db: DatabaseReference

    private val  chatMessageMng = ChatMessageManager()

    private lateinit var chatMessageInput: EditText
    private lateinit var chatMessageSendBtn: Button



    fun sendChatMessage(db: DatabaseReference, userId: String, email: String, message: String, imageUrl: String) {

        db.addValueEventListener(chatMessageMng.postListener)

        // TODO id採番させるか？
        db.child("chat_message").child(userId).updateChildren(
            mapOf(Pair("message", message),
                Pair("email", email),
                Pair("image_url",imageUrl)
            )
        ).addOnCompleteListener {
            Log.d("", "write was success")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_base)

        chatMessageInput = findViewById(R.id.chat_message_input)
        chatMessageSendBtn = findViewById(R.id.chat_message_send_btn)

        uid = intent.getStringExtra("uid")
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("password")
        message = chatMessageInput.text.toString()
        val imageUrl = ""

        db = FirebaseDatabase.getInstance().getReference("message")


        chatMessageSendBtn.setOnClickListener {
            sendChatMessage(db, uid, email, message, imageUrl)
            Log.d("", "${db}")
            Log.d("", "Chat Activity started successful!")
            Log.d("", "Now user email: ${email}")
            Log.d("", "Now user password: ${password}")

        }

    }

}
