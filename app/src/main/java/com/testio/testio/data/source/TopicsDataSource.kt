package com.testio.testio.data.source

import com.testio.testio.topics.TopicsContract

interface TopicsDataSource {
    fun getTopics()
    fun setPresenter(presenter: TopicsContract.Presenter)
}