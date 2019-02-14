package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.app.Activity
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_chat_base.*

class ChatActivity : Activity() {

    private lateinit var db: DatabaseReference
//    private lateinit var databaseAdapter : ChatViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_base)

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        db = FirebaseDatabase.getInstance().getReference()
        db.setValue("message").addOnCompleteListener {
            Log.d("", "access was succeed!")
        }

        Log.d("", "${db}")
        Log.d("", "Chat Activity started successful!")
        Log.d("", "Now user email: ${email}")
        Log.d("", "Now user password: ${password}")
    }

}
