package com.medcare.aknk.firebasechatapp.model

import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.FirebaseNetworkException
import java.lang.Exception

class ErrorMessageManager : OnFailureListener {
    lateinit var errorCode: Throwable
    lateinit var errorMessage: String

    override fun onFailure(p0: Exception) {
        try {
            errorCode = p0.cause!!
            errorMessage = p0.message!!

            Log.d("", "errorCode$errorCode")
            Log.d("", "errorMessage$errorMessage")
            returnErrMessage(p0)
        } catch(ex: Throwable) {
            Log.d("", "${ex.message}")
        }

    }

    fun returnErrMessage(exception: Exception): Pair<Throwable?, String?> {
        return Pair(exception.cause, exception.message)
    }
}
