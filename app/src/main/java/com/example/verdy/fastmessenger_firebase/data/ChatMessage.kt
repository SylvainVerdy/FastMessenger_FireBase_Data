package com.example.verdy.fastmessenger_firebase.data

class ChatMessage(val id: String, val text:String, val fromId:String, val toId:String, val timestamp: Long){
    constructor():this("","","","",-1)
}