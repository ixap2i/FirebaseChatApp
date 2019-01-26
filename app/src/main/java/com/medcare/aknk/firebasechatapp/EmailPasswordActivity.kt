package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_auth_main.*
import java.lang.Exception

class EmailPasswordActivity : AppCompatActivity()  {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_chat)

        val signUpEmail = findViewById<EditText>(R.id.fieldEmail)
        val signUpPassword = findViewById<EditText>(R.id.fieldPassword)

//        val userStatusBar = findViewById<TextView>(R.id.user_status)
        val signUpBtn = findViewById<Button>(R.id.button_singin)

        fun updateUI(user : FirebaseUser?) {
            val isSignedIn = user!= null
            if(isSignedIn) {
//                userStatusBar.text = user = user?.displayName?
            } else {
                Log.d("", "User data was losted")
            }
        }
        fun signOut() {
            auth.signOut()
            updateUI(null)
        }


        fun signIn(email: String, password: String) {

            val singin = auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    task ->
                if(task.isSuccessful) {
                    Log.d("", "login was succeed")
                    Log.d("", "${auth.currentUser}")
                    val user: FirebaseUser? = auth.currentUser
                    updateUI(user)

                } else if(task.isCanceled) {
                    Log.d("", "login was cenceled")
                    Log.d("", "${auth.currentUser}")
                } else {
                    Log.d("", "Exception occured: ${task.exception}")
                }
            }
        }

        fun createUser(email: String, password: String) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        Log.d("", "create user success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w("", "create user failed", it.exception)
                        updateUI(null)
                    }
                }
        }

        signUpBtn.setOnClickListener {
            val email = signUpEmail.text.toString()
            lateinit var password: String

            email.let {
                password =  signUpPassword.text.toString()
                signIn(email, password)

            } ?: run {
                Log.d("", "Email cannnot be empty")
            }



        }

    }

//    @Override
//    override fun onStart() {
//        super.onStart()
//        Log.d("", "test : ${auth}")
//        Log.d("", "current user ? : ${auth.currentUser}")
//
//        val currentUser = auth.currentUser
////        updateUI(currentUser)
////        signIn()
//    }


}

