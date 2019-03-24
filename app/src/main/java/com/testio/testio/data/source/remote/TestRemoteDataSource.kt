package com.testio.testio.data.source.remote

import android.util.Log
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
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
            .document("question$count")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    value = document.getString("title")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        val valueArray: BiMap<Int, String>? = HashBiMap.create()

        db.collection("topics/topic$topicId/questions$topicId/question$count/answers")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        valueArray?.put(document.data["id"].toString().toInt(), document.data["title"].toString())
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
                presenter?.loadData(value, valueArray)
            }

    }

    override fun setPresenter(presenter: TestContract.Presenter) {
        this.presenter = presenter
    }
}