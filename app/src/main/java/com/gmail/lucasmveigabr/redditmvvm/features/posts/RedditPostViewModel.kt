package com.gmail.lucasmveigabr.redditmvvm.features.posts

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.*
import com.gmail.lucasmveigabr.redditmvvm.data.api.RedditApi
import com.gmail.lucasmveigabr.redditmvvm.data.model.response.RedditChildrenData
import com.gmail.lucasmveigabr.redditmvvm.data.model.response.RedditResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RedditPostViewModel(val applicationContext: Application) :
    AndroidViewModel(applicationContext) {

    private val api: RedditApi

    private val lastResponse = MutableLiveData<RedditResponse>()


    private val _posts =
        Transformations.map(lastResponse) { input -> input.data.children.map { it.data } }
    val posts: LiveData<List<RedditChildrenData>> = _posts


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        api = retrofit.create(RedditApi::class.java)
        viewModelScope.launch {
            fetchPosts()
        }
    }

    fun nextPageClick() {
        viewModelScope.launch {
            fetchPosts()
        }
    }

    fun recyclerItemClick(item: RedditChildrenData) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
        applicationContext.startActivity(intent)
    }

    private suspend fun fetchPosts() {   //TODO: cleanup
        withContext(Dispatchers.IO) {
            if (lastResponse.value == null) {
                val response = api.getPosts("", "15").execute()
                if (response.isSuccessful) {
                    lastResponse.postValue(response.body())
                }
            } else {
                val response = api.getPosts(lastResponse.value!!.data.after!!, "15").execute()
                if (response.isSuccessful) {
                    lastResponse.postValue(response.body())
                }
            }
        }
    }

}
