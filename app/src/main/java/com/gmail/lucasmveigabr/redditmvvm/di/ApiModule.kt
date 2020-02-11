package com.gmail.lucasmveigabr.redditmvvm.di

import com.gmail.lucasmveigabr.redditmvvm.data.api.RedditApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(RedditApi::class.java)
    }
}