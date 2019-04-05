package com.testio.testio.features.topics

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.testio.testio.data.models.Item

interface TopicsContract{
    interface View{
        fun showTopics(options: FirestoreRecyclerOptions<Item>)
        fun setPresenter(presenter: Presenter)
    }
    interface Presenter{
        fun getTopics()
        fun loadTopics(options: FirestoreRecyclerOptions<Item>)
    }
}