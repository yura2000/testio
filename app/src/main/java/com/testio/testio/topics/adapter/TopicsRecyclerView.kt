package com.testio.testio.topics.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestoreException
import com.testio.testio.R
import com.testio.testio.data.Item
import com.testio.testio.topics.TopicsClickListener

class TopicsRecyclerView(options: FirestoreRecyclerOptions<Item>) :
    FirestoreRecyclerAdapter<Item, TopicsViewHolder>(options) {

    var listener: TopicsClickListener? = null
    private val TAG = "MyActivity"

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopicsViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.item_title, p0, false)

        return TopicsViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int, model: Item) {
//        val topic: Item? = null
//        topic?.id = model.id
//        topic?.title = model.title
//        holder.bind(topic)
        holder.topicTitle?.text = model.title
        holder.topicId?.text = model.id
        holder.containerItem?.setOnClickListener {
            listener?.onTopicsClicked(model)
        }
    }

    override fun onDataChanged() {
        super.onDataChanged()
    }

    override fun onError(e: FirebaseFirestoreException) {
        super.onError(e)
        Log.w(TAG, "Error getting documents.", e)
    }
}