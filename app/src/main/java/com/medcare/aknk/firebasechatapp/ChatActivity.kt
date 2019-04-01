package com.medcare.aknk.firebasechatapp

import android.os.Bundle
import android.app.Activity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Constraints
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import com.medcare.aknk.firebasechatapp.model.ChatMessage
import com.medcare.aknk.firebasechatapp.model.ChatViewAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class ChatActivity : Activity() {

    private lateinit var uid: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var message: String
    private lateinit var image_url: String
    private lateinit var created_at: Date
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private lateinit var dbRef: DatabaseReference

    private lateinit var chatMessageInput: EditText
    private lateinit var chatMessageSendBtn: Button

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    private var messageList: ArrayList<ChatMessage> = arrayListOf()
    lateinit var adapter: ChatViewAdapter

    lateinit var chatMessage: ChatMessage

    private val chatMessageConstainer = MutableLiveData<Pair<String, ChatMessage>>()

    val CONST_PREFERENCE_KEY_UID: String = "USER_UID"
    val CONST_PREFERENCE_KEY_PASS: String = "USER_PASSWRD"
    val CONST_PREFERENCE_KEY_USR_INFO: String = "USER_INFO"



    fun sendChatMessage(id: Integer, db: DatabaseReference, userId: String, email: String, message: String, imageUrl: String, createdAt: String) {

        db.child("chat_message").child(userId)
        db.child("chat_message").child(userId).child(id.toString()).setValue(
            mapOf(Pair("id", id),
                Pair("message", message),
                Pair("email", email),
                Pair("image_url",image_url),
                Pair("created_at", createdAt)
            )
        ).addOnCompleteListener {
            Log.d("", "write was success")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_base)

        val preference = getSharedPreferences(CONST_PREFERENCE_KEY_UID, AppCompatActivity.MODE_PRIVATE)

        preference?.let {
            val uid = preference.getString(CONST_PREFERENCE_KEY_UID, null)
            val pass = preference.getString(CONST_PREFERENCE_KEY_PASS, null)
            val info = preference.getStringSet(CONST_PREFERENCE_KEY_USR_INFO, null)
            Log.d("", "uid: $uid, pass: $pass")
            Log.d("", "usr info: $info")
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dbSnapShot: DataSnapshot?) {
                val chatObject: HashMap<String, Object> = dbSnapShot?.child("chat_message")?.child(uid)?.getValue(true) as HashMap<String, Object>

                var i = 1
                while(i < chatObject.size)  {
                    var message: HashMap<String, ChatMessage> = chatObject.get("$i")
//                    ChatViewAdapter(message as List<String>)
                    i++
                }
            }

            override fun onCancelled(dbErr: DatabaseError?) {
                Log.w(Constraints.TAG, "load post canceled", dbErr?.toException())
            }
        }

        val childListener = object : ChildEventListener {
            override fun onCancelled(dbErr: DatabaseError?) {
                Log.w("", "Error was occurred as follow reason: $dbErr")
            }

            override fun onChildMoved(dbSnapShot: DataSnapshot?, previewChild: String?) {
                Log.d("", "Here is dbSnapShot $dbSnapShot")
            }

            override fun onChildChanged(dbSnapShot: DataSnapshot?, previewChild: String?) {
            }

            override fun onChildAdded(dbSnapShot: DataSnapshot?, previewChild: String?) {
            }

            override fun onChildRemoved(dbSnapShot: DataSnapshot?) {
            }

        }


        chatMessageInput = findViewById(R.id.chat_message_input)
        chatMessageSendBtn = findViewById(R.id.chat_message_send_btn)
        recyclerView = findViewById(R.id.chat_recyclerView)
        messageList = arrayListOf()
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = ChatViewAdapter(messageList)


        uid = intent.getStringExtra("uid")
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("password")

        dbRef = FirebaseDatabase.getInstance().getReference("message")
        dbRef.child("chat_message").database

        dbRef.addValueEventListener(postListener)
        dbRef.addChildEventListener(childListener)

//        getChatMessages(uid, dbRef)

        chatMessageSendBtn.setOnClickListener {
            message = chatMessageInput.text.toString()
            image_url = ""
            created_at = Date()
            val date = dateFormat.format(created_at)


            chatMessage = ChatMessage(messageList.size,
                uid,
                message,
                email,
                image_url,
                date)
            messageList.add(chatMessage)


            sendChatMessage(Integer(messageList.size), dbRef, uid, email, message, image_url, date)

            adapter = ChatViewAdapter(messageList)

            linearLayoutManager.scrollToPosition(0)
            Log.d("", "Chat Activity started successful!")
            Log.d("", "Now user email: ${email}")
            Log.d("", "Now user password: ${password}")

        }

    }

}
