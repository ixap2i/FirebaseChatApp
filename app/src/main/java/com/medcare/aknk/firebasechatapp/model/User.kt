package com.medcare.aknk.firebasechatapp.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

data class User(
    val email: String,
    val password: String
) {
    fun createUser(email: String, password: String, auth: FirebaseAuth) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Log.d("", "create user success")
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    Log.w("", "create user failed", it.exception)
//                    updateUI(null)
                }
            }
    }

}