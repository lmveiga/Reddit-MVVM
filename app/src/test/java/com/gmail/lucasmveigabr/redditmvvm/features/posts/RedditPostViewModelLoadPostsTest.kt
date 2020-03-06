package com.gmail.lucasmveigabr.redditmvvm.features.posts

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gmail.lucasmveigabr.redditmvvm.data.api.RedditApi
import com.gmail.lucasmveigabr.redditmvvm.data.api.RedditApiTd
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class RedditPostViewModelLoadPostsTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val testDispatcher = TestCoroutineDispatcher()
    lateinit var api: RedditApi
    lateinit var context: Application
    lateinit var sut: RedditPostViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        api = RedditApiTd()
        context = mockk()
        sut = RedditPostViewModel(api, context, testDispatcher)
        sut.posts.observeForever { }  //so that live data gets updated
    }

    @Test
    fun `when load posts is called for first time and result is successful should update live data`() =
        testDispatcher.runBlockingTest {
            assertNull(sut.posts.value)
            sut.loadNewPosts()
            assertNotNull(sut.posts.value)
        }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}