package com.gmail.lucasmveigabr.redditmvvm.data.api

import com.gmail.lucasmveigabr.redditmvvm.data.model.response.RedditResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("/top.json")
    fun getPosts(@Query("after") after: String, @Query("limit") limit: String): Call<RedditResponse>

}