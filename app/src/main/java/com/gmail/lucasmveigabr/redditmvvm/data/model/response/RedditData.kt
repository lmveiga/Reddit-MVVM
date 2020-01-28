package com.gmail.lucasmveigabr.redditmvvm.data.model.response

data class RedditData(
    val children: List<RedditChildren>,
    val after: String?,
    val before: String?
)