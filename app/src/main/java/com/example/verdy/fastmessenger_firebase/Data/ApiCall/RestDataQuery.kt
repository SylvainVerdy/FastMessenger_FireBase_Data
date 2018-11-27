package com.example.verdy.fastmessenger_firebase.Data.ApiCall

import io.reactivex.Observable
import retrofit2.http.GET


interface RestDataQuery {

    @GET("v1/assets/?apikey=0E78570B-A3A4-4C53-B3C1-27FF7577286E")

    fun getAllPosts(): Observable<List<Post>>


}