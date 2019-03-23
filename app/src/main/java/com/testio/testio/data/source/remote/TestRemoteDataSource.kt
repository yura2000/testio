package com.testio.testio.data.source.remote

import android.support.annotation.NonNull
import android.util.Log
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.testio.testio.data.source.TestDataSource
import com.testio.testio.test.TestContract
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.testio.testio.data.Item
import com.google.firebase.firestore.QueryDocumentSnapshot



class TestRemoteDataSource : TestDataSource {

    private var presenter: TestContract.Presenter? = null
    private var TAG = "MyData"

    override fun getData(topicId: String?, count: Int?) {
        val db = FirebaseFirestore.getInstance()

        Log.w(TAG, "$topicId")

        var value: String? = ""

        db.collection("topics")
            .document("topic$topicId")
            .collection("questions$topicId")
            .whereEqualTo("id", "$count")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        value = document.data["title"].toString()
                        presenter?.loadData(value)
                        Log.w(TAG, "$value")
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
        if (value != "")
            presenter?.loadData(value)
    }

    override fun setPresenter(presenter: TestContract.Presenter) {
        this.presenter = presenter
    }
}