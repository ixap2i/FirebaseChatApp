package com.medcare.aknk.firebasechatapp.model

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.room.Embedded
import androidx.room.Relation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*


data class ChatMessage(
    val userName: String,
    var chatMessage: String,
    val email: String,
    val ImageUrl: String?,
    val createdAt: String
) {

    @Embedded
    lateinit var message: Message

    @Relation(parentColumn = "id", entityColumn = "message_id")
    var messages: List<ChatMessage> = listOf()

}

class ChatMessageManager(
//    val hash_id: String,
//    var message: String,
//    var image_url: String,
//    val uid: String
) {

    val postListener = object : ValueEventListener {
        override fun onDataChange(dbSnapShot: DataSnapshot?) {
            val postData = dbSnapShot?.getValue(ChatMessage::class.java)
            Log.d("", "write was success")
        }

        override fun onCancelled(dbErr: DatabaseError?) {
            Log.w(Constraints.TAG, "load post canceled", dbErr?.toException())
        }
    }
}

class Message {

}
