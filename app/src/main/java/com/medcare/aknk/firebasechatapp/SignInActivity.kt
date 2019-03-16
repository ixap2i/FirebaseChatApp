package com.medcare.aknk.firebasechatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.*
import java.lang.Exception

class SignInActivity : AppCompatActivity()  {

    val CONST_PREFERENCE_KEY_USR_INFO: String = "USER_INFO"
    val CONST_PREFERENCE_KEY_UID: String = "USER_UID"
    val CONST_PREFERENCE_KEY_PASS: String = "USER_PASSWRD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_auth_main)
        val builder: FirebaseOptions.Builder = FirebaseOptions.Builder()
        builder
            .setApplicationId("fir-chatapp-b5c26")
            .setApiKey("")


        val userEmail = findViewById(R.id.fieldEmail) as EditText
        val userPassword = findViewById(R.id.fieldPassword) as EditText
        var auth: FirebaseAuth = FirebaseAuth.getInstance()

        val signUpBtn = findViewById(R.id.button_singin) as Button
        val snackBarPont = findViewById(R.id.snackBarMessage) as LinearLayout

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

                        val preference = getSharedPreferences(CONST_PREFERENCE_KEY_USR_INFO, AppCompatActivity.MODE_PRIVATE)
                        val editor = preference.edit()

                        editor.putString(CONST_PREFERENCE_KEY_UID, email)
                        editor.putString(CONST_PREFERENCE_KEY_PASS, password)

                        editor.apply()

                        startActivity(intent)
                        Log.d("", "Activity was up")
                    }

                } else if(task.isCanceled) {
                    Log.d("", "login was canceled")
                    Log.d("", "${auth.currentUser}")
                } else {
                    Log.d("", "Exception occurred: ${task.exception}")
                    if((task.exception as FirebaseAuthInvalidCredentialsException).errorCode == "ERROR_WRONG_PASSWORD") {
                        val snackbar = Snackbar.make(snackBarPont, "パスワードに誤りがあります。", Snackbar.LENGTH_INDEFINITE)

                        snackbar.show()
                    } else if((task.exception as FirebaseAuthInvalidUserException).errorCode == "ERROR_USER_NOT_FOUND") {
                        val snackbar = Snackbar.make(snackBarPont, "ユーザーが見つかりません。管理者に問い合わせいただくか、もう一度新規登録からお試しください。  ", Snackbar.LENGTH_INDEFINITE)
                        snackbar.show()
                    } else if("${task.exception}".contains("com.google.firebase.FirebaseNetworkException")) {
                        val snackbar = Snackbar.make(snackBarPont, "ネットワークエラーです。接続環境を今一度ご確認ください。", Snackbar.LENGTH_INDEFINITE)
                        snackbar.show()
                    }
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

