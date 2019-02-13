package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.app.Activity
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.widget.Button

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
//    , View.OnTouchListener {
    // TODO implement clicked button listener
//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        if(v!!.isClickable) {
////            return v.accessibilityClassName
//        }
//
//    }

    private lateinit var createUserBtn: Button
    private lateinit var signInUserBtn: Button
    private lateinit var chatRoomBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createUserBtn = findViewById(R.id.top_button_create_user)
        signInUserBtn = findViewById(R.id.top_button_signin_user)
        chatRoomBtn = findViewById(R.id.top_button_chat)

        val createUserBtnListener = createUserBtn.setOnClickListener {  }
        val signInUserBtnListener = signInUserBtn.setOnClickListener {  }
        val chatRoomBtnListener = chatRoomBtn.setOnClickListener {  }


    }

}
