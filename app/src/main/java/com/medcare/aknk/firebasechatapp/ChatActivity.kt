package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.app.Activity
import android.util.Log
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

    fun sendChatMessage(db: DatabaseReference, userId: String, email: String, message: String, imageUrl: String) {

        db.addValueEventListener(chatMessageMng.postListener)


        db.child("chat_message").setValue(userId)
        db.child("chat_message").child(userId).child("user_name").setValue(email)
        db.child("chat_message").child(userId).child("message").setValue(message)
        db.child("chat_message").child(userId).child("image_url").setValue(imageUrl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_base)

        uid = intent.getStringExtra("uid")
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("password")
        message = "Test"
        val imageUrl = ""

        db = FirebaseDatabase.getInstance().getReference("message")

        sendChatMessage(db, uid, email, message, imageUrl)

        Log.d("", "${db}")
        Log.d("", "Chat Activity started successful!")
        Log.d("", "Now user email: ${email}")
        Log.d("", "Now user password: ${password}")
    }

}
