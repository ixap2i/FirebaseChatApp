package com.medcare.aknk.firebasechatapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId


class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val service = MyFirebaseInstanceIdService()
        Log.d("check token", "Refreshed token: " + service.onNewToken())


//        val service: FirebaseInstanceId = FirebaseInstanceId()

//        service()
    }
}
