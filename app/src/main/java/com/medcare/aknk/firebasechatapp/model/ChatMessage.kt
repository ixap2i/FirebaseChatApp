package com.medcare.aknk.firebasechatapp.model

import androidx.room.*
import javax.annotation.Nullable

@Entity
@Nullable
data class ChatMessage(
    val id: Integer?,
    val userName: String,
    var chatMessage: String,
    val email: String,
    val ImageUrl: String?,
    val createdAt: String
) {

    @Relation(parentColumn = "id", entityColumn = "message_id")
    var messages: List<ChatMessage> = listOf()

    val postListener = object : ValueEventListener {
        override fun onDataChange(dbSnapShot: DataSnapshot?) {
            val postData = dbSnapShot?.getValue(ChatMessage::class.java)
            Log.d("", "Value is $postData")
        }

        override fun onCancelled(dbErr: DatabaseError?) {
            Log.w(Constraints.TAG, "load post canceled", dbErr?.toException())
        }
    }
}

