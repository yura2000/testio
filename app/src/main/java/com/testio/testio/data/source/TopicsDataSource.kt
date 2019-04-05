package com.testio.testio.data.source

import com.testio.testio.features.topics.TopicsContract

interface TopicsDataSource {
    fun getTopics()
    fun setPresenter(presenter: TopicsContract.Presenter)
}