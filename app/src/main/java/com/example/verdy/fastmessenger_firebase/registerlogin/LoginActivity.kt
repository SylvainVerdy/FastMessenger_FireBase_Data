package com.example.verdy.fastmessenger_firebase.registerlogin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.verdy.fastmessenger_firebase.Data.ApiCall.RestDataStorage
import com.google.firebase.auth.FirebaseAuth
import com.example.verdy.fastmessenger_firebase.R
import com.example.verdy.fastmessenger_firebase.messages.LatestMessageActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_login)

    api_button.setOnClickListener {
      val intent = Intent(this, RestDataStorage::class.java)
      startActivity(intent)
    }

    login_button_login.setOnClickListener {
      performLogin()
      Redirection()

    }

    back_to_register_textview.setOnClickListener{
      finish()
    }
  }

  private fun Redirection(){
    val intent = Intent(this, LatestMessageActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
  }
  private fun performLogin() {
    val email = email_edittext_login.text.toString()
    val password = password_edittext_login.text.toString()

    if (email.isEmpty() || password.isEmpty()) {
      Toast.makeText(this, "Please fill out email/pw.", Toast.LENGTH_SHORT).show()
      return
    }

    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
          if (!it.isSuccessful) return@addOnCompleteListener

          Log.d("Login", "Successfully logged in: ${it.result!!.user.uid}")

          val intent = Intent(this, LatestMessageActivity::class.java)
          intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
          startActivity(intent)
        }
        .addOnFailureListener {
          Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()
        }
  }

}