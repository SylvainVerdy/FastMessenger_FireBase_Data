package com.example.verdy.fastmessenger_firebase.Presentation

import com.example.verdy.fastmessenger_firebase.R
import com.example.verdy.fastmessenger_firebase.data.ChatMessage
import com.example.verdy.fastmessenger_firebase.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.latest_message_row.view.*

class LastestMessageRow(val chatMessage: ChatMessage): Item<ViewHolder>(){
    var chatPartnerUser:User? = null
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.message_text_view_latest_messages.text = chatMessage.text
        val chatPartnerId:String
        if(chatMessage.fromId == FirebaseAuth.getInstance().uid){
            chatPartnerId = chatMessage.toId
        }
        else{
            chatPartnerId = chatMessage.fromId
        }
        val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                chatPartnerUser = p0.getValue(User::class.java)
                viewHolder.itemView.user_textview_latest_message.text = chatPartnerUser?.username
                val targetImageView = viewHolder.itemView.image_image_view_latest_messages
                Picasso.get().load(chatPartnerUser?.profileImageUrl).into(targetImageView)
            }
        })
        viewHolder.itemView.user_textview_latest_message.text = chatMessage.toId
    }
    override fun getLayout(): Int {
        return R.layout.latest_message_row
    }
}