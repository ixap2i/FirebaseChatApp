package com.medcare.aknk.firebasechatapp

import android.util.JsonToken
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.InstanceIdResult
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage



class MyFirebaseInstanceIdService: FirebaseMessagingService() {


    val TAG = ""

    fun onNewToken(): Task<InstanceIdResult> {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().getInstanceId()


        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { result ->
            val token = result.token
        }

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken)


        return refreshedToken
    }

    private fun sendRegistrationToServer(token: String) {

    }
//
//    fun onMessageReceived(remoteMessage: RemoteMessage) {
//        // ...
//
//        // TODO(developer): Handle FCM messages here.
//        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d(TAG, "From: " + remoteMessage.from!!)
//
//        // Check if message contains a data payload.
//        if (remoteMessage.data.size > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.data)
//
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob()
//            } else {
//                // Handle message within 10 seconds
//                handleNow()
//            }
//
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.notification != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body!!)
//        }
//
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.
//    }
}