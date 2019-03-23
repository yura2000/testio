package com.testio.testio.test

import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.testio.testio.data.Item

interface TestContract {
    interface View {
        fun showData(value: String?)
        fun showGetDataError(resId: Int)
        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun getData(topicId: String?, count: Int?)
        fun loadData(value: String?)
    }
}