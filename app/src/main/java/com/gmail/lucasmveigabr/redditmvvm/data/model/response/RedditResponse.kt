package com.gmail.lucasmveigabr.redditmvvm.data.model.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RedditResponse(val data: RedditData)