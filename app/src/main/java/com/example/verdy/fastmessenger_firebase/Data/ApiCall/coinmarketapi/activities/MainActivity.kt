package com.example.verdy.fastmessenger_firebase.Data.ApiCall.actvities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.verdy.fastmessenger_firebase.Data.ApiCall.adapter.CryptoAdapter
import com.example.verdy.fastmessenger_firebase.Data.ApiCall.model.CryptoModel
import com.example.verdy.fastmessenger_firebase.Data.ApiCall.utils.Constants
import com.example.verdy.fastmessenger_firebase.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.api_recycler_view.*

import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    /**
     * Declare variables
     */
    private lateinit var adapter: CryptoAdapter

    /**
     * holds the REST endpoint to query,
     * with the “limit=10” parameter passed.
     * This will list the top 10 coins.
     */
    private val apiUrl = Constants.apiUrl

    /**
     * Using Kotlin Lazy delegator so variables
     * will be initialized when they are first called.
     */
    private val client by lazy {
        OkHttpClient()
    }

    private val request by lazy {
        /**
         * Takes in the apiUrl and returns the request object.
         */
        Request.Builder()
            .url(apiUrl)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.api_recycler_view)
        cryptoRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CryptoAdapter()
        cryptoRecyclerView.adapter = adapter
        getCoins()
    }

    fun getCoins() {
        /**
         *  enqueue ensures that this call does not occur on the Main UI thread,
         *  network transactions should be done off the Main UI Thread
         */
        client.newCall(request).enqueue(object : Callback {
            /**
             *  Triggered if an error occurred
             */
            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed ${e?.toString()}")
            }

            /**
             *  Has the results from the call made to the REST endpoint
             */
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println("Body: ${body}")
                val gson = Gson()
                println("gson = ${gson}")
                val cryptoCoins: List<CryptoModel> = gson.fromJson(body, object : TypeToken<List<CryptoModel>>() {}.type)

                /**
                 * Because the enqueue function was used,
                 * we must make this call to have the results returned
                 * to the main thread and then sent
                 * to the RecyclerView adapter
                 */
                runOnUiThread {
                    adapter.updateData(cryptoCoins)
                }
            }
        })
    }
}