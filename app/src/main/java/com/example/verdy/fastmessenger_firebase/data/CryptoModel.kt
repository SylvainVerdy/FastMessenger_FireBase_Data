package com.example.verdy.fastmessenger_firebase.Data.ApiCall.model

import android.content.DialogInterface
import com.google.gson.annotations.SerializedName


data class CryptoModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("price_eur")
    val price_eur: String,
    @SerializedName("percent_change_1h")
    val percent_change_1h: String,
    @SerializedName("percent_change_24h")
    val percent_change_24h: String,
    @SerializedName("percent_change_7d")
    val percent_change_7d: String,
    val setOnClickListener: DialogInterface.OnClickListener
)