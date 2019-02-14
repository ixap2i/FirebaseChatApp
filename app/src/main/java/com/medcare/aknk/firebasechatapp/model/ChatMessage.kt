package com.medcare.aknk.firebasechatapp.model

import androidx.room.Embedded
import androidx.room.Relation

class ChatMessage {

    @Embedded
    lateinit var message: Message

    @Relation(parentColumn = "id", entityColumn = "message_id")
    var messages: List<ChatMessage> = arrayListOf()
}

class Message {

}