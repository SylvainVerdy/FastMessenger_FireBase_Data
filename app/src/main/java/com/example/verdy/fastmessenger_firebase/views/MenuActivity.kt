package com.example.verdy.fastmessenger_firebase.views


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.verdy.fastmessenger_firebase.Data.ApiCall.actvities.MainActivity
import com.example.verdy.fastmessenger_firebase.R
import com.example.verdy.fastmessenger_firebase.messages.LatestMessagesActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        api_button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        messageButton.setOnClickListener {
            val intent = Intent(this, LatestMessagesActivity::class.java)
            startActivity(intent)
        }
    }
}
