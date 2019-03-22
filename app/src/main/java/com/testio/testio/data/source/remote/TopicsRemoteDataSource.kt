package com.testio.testio.data.source.remote

import android.util.Log
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.testio.testio.data.source.TopicsDataSource
import com.testio.testio.topics.TopicsContract
import com.google.firebase.firestore.FirebaseFirestore
import com.testio.testio.data.Item

class TopicsRemoteDataSource : TopicsDataSource {

    var db = FirebaseFirestore.getInstance()

    private var presenter: TopicsContract.Presenter? = null
    private val TAG = "MyActivity"

    override fun getTopics() {

        val query = FirebaseFirestore.getInstance()
            .collection("topics")
            .orderBy("id")

        val options = FirestoreRecyclerOptions.Builder<Item>()
            .setQuery(query, Item::class.java)
            .build()

        presenter?.loadTopics(options)
    }

    override fun setPresenter(presenter: TopicsContract.Presenter) {
        this.presenter = presenter
    }
}