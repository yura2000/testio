package com.testio.testio.topics.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.testio.testio.R
import com.testio.testio.data.Item
import com.testio.testio.topics.TopicsClickListener
import io.realm.Realm.init

class TopicsViewHolder(itemView: View, private val onClickItem: TopicsClickListener?) :
    RecyclerView.ViewHolder(itemView) {

    var topicTitle: TextView? = null
    var topicId: TextView? = null
    var containerItem: ConstraintLayout? = null

    init {
        topicTitle = itemView.findViewById(R.id.itemTitle_tv)
        topicId = itemView.findViewById(R.id.itemId_tv)
        containerItem = itemView.findViewById(R.id.containerItem)
    }

    fun bind(topic: Item?) {
        topicTitle?.text = topic?.title
        topicId?.text = topic?.id.toString()
//        containerItem?.setOnClickListener {
//            onClickItem?.onTopicsClicked(topic)
//        }
    }
}