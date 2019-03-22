package com.testio.testio.topics

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

import com.testio.testio.data.Item
import com.testio.testio.topics.adapter.TopicsRecyclerView
import kotlinx.android.synthetic.main.topics_fragment.*

import com.firebase.ui.firestore.FirestoreRecyclerAdapter

import com.testio.testio.topics.adapter.TopicsViewHolder


class TopicsFragment : Fragment(), TopicsContract.View {

    private var presenter: TopicsContract.Presenter? = null
    private lateinit var callback: TopicsClickListener
    var topicsAdapter: TopicsRecyclerView? = null
    private val TAG = "MyFrag"

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callback = context as TopicsClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(com.testio.testio.R.layout.topics_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.getTopics()
    }

    override fun onStart() {
        super.onStart()
        topicsAdapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        topicsAdapter?.stopListening()
    }

    override fun showTopics(options: FirestoreRecyclerOptions<Item>) {
        topicsAdapter = TopicsRecyclerView(options)

        val topicsRecyclerView = topics_recycler_view
        topicsAdapter!!.listener = (object : TopicsClickListener {
            override fun onTopicsClicked(item: Item?) {
                callback.onTopicsClicked(item)
            }
        })
        topicsRecyclerView?.layoutManager = LinearLayoutManager(context)
        topicsRecyclerView?.adapter = topicsAdapter
    }

    override fun setPresenter(presenter: TopicsContract.Presenter) {
        this.presenter = presenter
    }
}
