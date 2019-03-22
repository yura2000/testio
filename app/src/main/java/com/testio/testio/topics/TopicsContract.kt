package com.testio.testio.topics

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.testio.testio.data.Item

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