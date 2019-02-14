package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)


        val builder: FirebaseOptions.Builder = FirebaseOptions.Builder()
        builder
            .setApplicationId("fir-chatapp-b5c26")
            .setApiKey("AIzaSyCb963RsrC7atucAtC_0Ng7sLRPKwm7GL4")


        val userEmail = findViewById<EditText>(R.id.fieldEmail)
        val userPassword = findViewById<EditText>(R.id.fieldPassword)
        var auth: FirebaseAuth = FirebaseAuth.getInstance()

        val email = userEmail.toString()
        val password = userPassword.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener {
                it.addOnCanceledListener {  }
                it.addOnSuccessListener { Log.d("", "Create user succeed: ${it.user.providerData.forEach {
                    it
                }}") }
                it.addOnFailureListener { Log.w("", "Create user was failed as follow reason: ${Thread.dumpStack()}") }
//                it.addOnCompleteListener {  }
            })
        val signUpBtn = findViewById(R.id.button_singin) as Button

    }
}
