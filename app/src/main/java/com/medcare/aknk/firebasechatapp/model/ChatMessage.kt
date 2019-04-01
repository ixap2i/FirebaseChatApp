package com.medcare.aknk.firebasechatapp.model

import androidx.room.*
import javax.annotation.Nullable

@Entity
@Nullable
data class ChatMessage(
    @PrimaryKey
    private val id: Int = 0,
    private val message: String,
    private val email: String,
    private val created_at: String
)

@Dao
interface ChatMsgDao {
    @Query("select * from chatmessage")
    fun getAll(): List<ChatMessage>
}