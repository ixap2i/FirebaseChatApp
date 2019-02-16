package com.medcare.aknk.firebasechatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.*
import java.lang.Exception

class SignInActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_auth_main)
        val builder: FirebaseOptions.Builder = FirebaseOptions.Builder()
        builder
            .setApplicationId("fir-chatapp-b5c26")
            .setApiKey("AIzaSyCb963RsrC7atucAtC_0Ng7sLRPKwm7GL4")


        val userEmail = findViewById<EditText>(R.id.fieldEmail)
        val userPassword = findViewById<EditText>(R.id.fieldPassword)
        var auth: FirebaseAuth = FirebaseAuth.getInstance()

        val signUpBtn = findViewById(R.id.button_singin) as Button

        fun updateUI(user : FirebaseUser?) {
            val isSignedIn = user!= null
            if(isSignedIn) {
//                userStatusBar.text = user = user?.displayName?
            } else {
                Log.d("", "User data was lost")
            }
        }
        fun signOut() {
            auth.signOut()
            updateUI(null)
        }


        fun signIn(email: String, password: String, intent: Intent) {

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    task ->
                if(task.isSuccessful) {
                    Log.d("", "login was succeed")
                    Log.d("", "${auth.currentUser}")
                    val user: FirebaseUser? = auth.currentUser
                    updateUI(user)

                    GlobalScope.launch {
                        intent.putExtra("uid", auth.uid)
                        intent.putExtra("email", email)
                        intent.putExtra("password", password)
                        startActivity(intent)
                        Log.d("", "Activity was up")
                    }

                } else if(task.isCanceled) {
                    Log.d("", "login was canceled")
                    Log.d("", "${auth.currentUser}")
                } else {
                    Log.d("", "Exception occurred: ${task.exception}")
                }

            }

        }


        signUpBtn.setOnClickListener {
            lateinit var email: String
            lateinit var password: String

            try {
                password =  userPassword.text.toString()
                email = userEmail.text.toString()
                val componentsValid = password.isNullOrEmpty() && email.isNullOrEmpty()

                // TODO toast or snackbar
                componentsValid.let {
                    val intent = Intent(this, ChatActivity::class.java)

                    runBlocking {
                        signIn(email, password, intent)
                    }

                }

            } catch(ex: Exception) {
                Log.d("", "Exception occurred : ${ex}")
            }

        }


    }
}

