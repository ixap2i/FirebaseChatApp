package com.medcare.aknk.firebasechatapp.model

import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.room.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import javax.annotation.Nullable

@Entity
@Nullable
data class ChatMessage(
    val id: Int?,
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

    companion object {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val picRef = storageRef.child("/storage/emulated/0/DCIM/Camera")
    }
}

