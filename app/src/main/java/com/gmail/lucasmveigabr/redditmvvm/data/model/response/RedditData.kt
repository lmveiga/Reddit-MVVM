package com.gmail.lucasmveigabr.redditmvvm.data.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RedditData(
    val children: List<RedditChildren>,
    val after: String?,
    val before: String?
)