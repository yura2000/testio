package com.testio.testio.topics

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.testio.testio.data.Item
import com.testio.testio.data.source.TopicsDataSource

class TopicsPresenter(topicsView: TopicsContract.View, topicsData: TopicsDataSource)
    : TopicsContract.Presenter {

    private var mTopicsRemoteDataSource: TopicsDataSource? = null
    private var mTopicsView: TopicsContract.View? = null

    init {
        mTopicsView = topicsView
        mTopicsRemoteDataSource = topicsData

        mTopicsView?.setPresenter(this)
        mTopicsRemoteDataSource?.setPresenter(this)
    }

    override fun getTopics() {
        mTopicsRemoteDataSource?.getTopics()
    }

    override fun loadTopics(options: FirestoreRecyclerOptions<Item>) {
        mTopicsView?.showTopics(options)
    }
}