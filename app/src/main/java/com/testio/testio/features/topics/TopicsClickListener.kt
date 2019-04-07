package com.testio.testio.features.topics

import com.testio.testio.models.Item

interface TopicsClickListener {
    fun onTopicsClicked(item: Item?)
}