package com.gmail.lucasmveigabr.redditmvvm.features.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.lucasmveigabr.redditmvvm.R
import kotlinx.android.synthetic.main.reddit_post_fragment.*

class RedditPostFragment : Fragment() {

    companion object {
        fun newInstance() = RedditPostFragment()
    }

    private lateinit var viewModel: RedditPostViewModel
    private lateinit var adapter: RedditPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.reddit_post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RedditPostViewModel::class.java)
        adapter = RedditPostAdapter(requireContext()) {
            viewModel.recyclerItemClick(it)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        viewModel.posts.observe(viewLifecycleOwner, Observer {
            adapter.setPosts(it)
        })
        nextPageButton.setOnClickListener {
            viewModel.nextPageClick()
        }
    }

}
