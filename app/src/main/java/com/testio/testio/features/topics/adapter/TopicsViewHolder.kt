package com.testio.testio.features.topics.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.testio.testio.R
import com.testio.testio.features.topics.TopicsClickListener

class TopicsViewHolder(itemView: View, private val onClickItem: TopicsClickListener?) :
    RecyclerView.ViewHolder(itemView) {

    var topicTitle: TextView? = null
    var topicId: TextView? = null
    var topicImg: ImageView? = null
    var containerItem: ConstraintLayout? = null

    init {
        topicTitle = itemView.findViewById(R.id.itemTitle_tv)
        topicId = itemView.findViewById(R.id.itemId_tv)
        topicImg = itemView.findViewById(R.id.itemImage_img)
        containerItem = itemView.findViewById(R.id.containerItem)
    }
}