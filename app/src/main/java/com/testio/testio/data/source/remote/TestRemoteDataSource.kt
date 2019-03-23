package com.testio.testio.data.source.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.testio.testio.data.source.TestDataSource
import com.testio.testio.test.TestContract


class TestRemoteDataSource : TestDataSource {

    private var presenter: TestContract.Presenter? = null
    private var TAG = "MyData"

    override fun getData(topicId: String?, count: Int?) {
        val db = FirebaseFirestore.getInstance()

        Log.w(TAG, "$topicId")

        var value: String? = ""

        db.collection("topics/topic$topicId/questions$topicId")
            .whereEqualTo("id", "$count")
            .get()
            .addOnCompleteListener { task ->
        //        if (task.isSuccessful) {
                    for (document in task.result!!) {
                        value = document.data["title"].toString()

                        Log.w(TAG, "$value")
                    }
//                } else {
//                    Log.w(TAG, "Error getting documents.", task.exception)
//                }
            }

        val valueArray: MutableList<String>? = mutableListOf()

        db.collection("topics/topic$topicId/questions$topicId/question$count/answers")
            .get()
            .addOnCompleteListener { task ->
       //         if (task.isSuccessful) {
                    for (document in task.result!!) {
                        valueArray?.add(document.data["title"].toString())
                        Log.w(TAG, "$value")
                    }
//                } else {
//                    Log.w(TAG, "Error getting documents.", task.exception)
//                }
                presenter?.loadData(value, valueArray)
            }

    }

    override fun setPresenter(presenter: TestContract.Presenter) {
        this.presenter = presenter
    }
}