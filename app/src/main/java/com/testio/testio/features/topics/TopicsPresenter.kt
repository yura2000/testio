package com.testio.testio.features.topics

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.testio.testio.models.Item
import com.testio.testio.data.source.TopicsDataSource

class TopicsPresenter(topicsView: TopicsContract.View, topicsData: TopicsDataSource) : TopicsContract.Presenter {

    private var topicsData: TopicsDataSource? = null
    private var topicsView: TopicsContract.View? = null

    init {
        this.topicsView = topicsView
        this.topicsData = topicsData

        this.topicsView?.setPresenter(this)
        this.topicsData?.setPresenter(this)
    }

    override fun getTopics() {
        topicsData?.getTopics()
    }

    override fun loadTopics(options: FirestoreRecyclerOptions<Item>) {
        topicsView?.showTopics(options)
    }
}