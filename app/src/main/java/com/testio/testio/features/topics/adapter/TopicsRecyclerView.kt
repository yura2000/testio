package com.testio.testio.features.topics.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestoreException
import com.squareup.picasso.Picasso
import com.testio.testio.R
import com.testio.testio.models.Item
import com.testio.testio.features.topics.TopicsClickListener

class TopicsRecyclerView(options: FirestoreRecyclerOptions<Item>) :
    FirestoreRecyclerAdapter<Item, TopicsViewHolder>(options) {

    var listener: TopicsClickListener? = null
    private val TAG = "MyActivity"

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopicsViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.item_title, p0, false)

        return TopicsViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int, model: Item) {
        if (model.title != "") holder.topicTitle?.text = model.title
        else holder.topicTitle?.text = "Topic = null. Помилка отримання даних. Повідомте розробнику!"

        if (model.id != "") holder.topicId?.text = model.id
        else holder.topicTitle?.text = "ID = null. Помилка отримання даних. Повідомте розробнику!"

        if (model.image != "") Picasso.get().load(model.image).error(R.mipmap.error_sm).placeholder(R.drawable.progress_animation).into(holder.topicImg)
        else Picasso.get().load(R.mipmap.brain).into(holder.topicImg)

        holder.containerItem?.setOnClickListener {
            listener?.onTopicsClicked(model)
        }
    }

    override fun onError(e: FirebaseFirestoreException) {
        super.onError(e)
        Log.w(TAG, "Error getting documents.", e)
    }
}