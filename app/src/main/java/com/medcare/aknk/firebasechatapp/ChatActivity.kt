package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.app.Activity
import android.util.Log

import kotlinx.android.synthetic.main.activity_chat_base.*

class ChatActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_base)

        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        Log.d("", "Chat Activity started successful!")
        Log.d("", "Now user email: ${email}")
        Log.d("", "Now user password: ${password}")
    }

}
