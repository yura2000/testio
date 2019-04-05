package com.testio.testio.features.topics

import com.testio.testio.data.models.Item

interface TopicsClickListener {
    fun onTopicsClicked(item: Item?)
}