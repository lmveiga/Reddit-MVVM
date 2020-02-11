package com.gmail.lucasmveigabr.redditmvvm.di

import com.gmail.lucasmveigabr.redditmvvm.features.posts.RedditPostViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        RedditPostViewModel(get(), get())
    }
}