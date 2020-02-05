package com.gmail.lucasmveigabr.redditmvvm.data.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RedditChildrenData(
    val author: String,
    val title: String,
    val thumbnail: String,
    val url: String
)