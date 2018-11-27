package com.example.verdy.fastmessenger_firebase.models


class User(val uid: String, val username: String, val profileImageUrl: String) {
  constructor() : this("", "", "")
}