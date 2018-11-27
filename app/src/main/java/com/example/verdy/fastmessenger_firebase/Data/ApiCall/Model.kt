package com.example.verdy.fastmessenger_firebase.Data.ApiCall

import com.google.gson.annotations.SerializedName


data class Post(
    @SerializedName("asset_id") val userId: String,
    @SerializedName("name") val name: String
)