package com.gmail.lucasmveigabr.redditmvvm.data.api

import com.gmail.lucasmveigabr.redditmvvm.data.model.response.RedditChildren
import com.gmail.lucasmveigabr.redditmvvm.data.model.response.RedditChildrenData
import com.gmail.lucasmveigabr.redditmvvm.data.model.response.RedditData
import com.gmail.lucasmveigabr.redditmvvm.data.model.response.RedditResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RedditApiTd : RedditApi {

    var isFailure = false
    var lastCallAfterValue: String? = null

    override fun getPosts(after: String, limit: String): Call<RedditResponse> {
        return object : Call<RedditResponse> {

            override fun execute(): Response<RedditResponse> {
                lastCallAfterValue = after
                if (isFailure)
                    return Response.error(500, null)
                if (after == "after") {
                    return Response.success(
                        RedditResponse(
                            RedditData(
                                listOf(
                                    RedditChildren(
                                        RedditChildrenData("author", "title", "thumbnail", "url")
                                    )
                                ), "after2", "after"
                            )
                        )
                    )
                }
                return Response.success(RedditResponse(RedditData(emptyList(), "after", "before")))
            }


            override fun enqueue(callback: Callback<RedditResponse>) {
                throw RuntimeException("Not Implemented")
            }

            override fun isExecuted(): Boolean {
                throw RuntimeException("Not Implemented")
            }

            override fun clone(): Call<RedditResponse> {
                throw RuntimeException("Not Implemented")
            }

            override fun isCanceled(): Boolean {
                throw RuntimeException("Not Implemented")
            }

            override fun cancel() {
                throw RuntimeException("Not Implemented")
            }

            override fun request(): Request {
                throw RuntimeException("Not Implemented")
            }

        }
    }

}