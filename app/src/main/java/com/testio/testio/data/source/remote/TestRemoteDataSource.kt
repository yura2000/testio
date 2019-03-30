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

        val valueArray: BiMap<Int, String>? = HashBiMap.create()

        db.collection("topics/topic$topicId/questions$topicId/question$count/answers")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        valueArray?.put(document.data["id"].toString().toInt(), document.data["title"].toString())
                    }
                    presenter?.loadData(valueArray)
                } else {
                    presenter?.dataLoadingError()
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }

    override fun getDocumentName(topicId: String?, count: Int?) {
        val db = FirebaseFirestore.getInstance()

        db.collection("topics/topic$topicId/questions$topicId")
            .document("question$count")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val value = document.getString("title")
                    Log.d(TAG, "Name: $value, count $count")
                    presenter?.loadDocumentName(value)
                } else {
                    presenter?.dataLoadingError()
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                presenter?.dataLoadingError()
                Log.d(TAG, "get failed with ", exception)
            }
    }

    override fun getDocumentsCount(topicId: String?) {
        val db = FirebaseFirestore.getInstance()
        var amountOfDocuments: Int

        db.collection("topics/topic$topicId/questions$topicId")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    amountOfDocuments = task.result!!.size()
                    presenter?.loadDocumentsCount(amountOfDocuments)
                } else {
                    presenter?.dataLoadingError()
                    Log.w(TAG, "Error getting documents.", task.exception)
                }

            }
    }

    override fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?) {
        val db = FirebaseFirestore.getInstance()

        val user = HashMap<String, String>()
        user["correct"] = "$correctAnswers"
        user["in_correct"] = "$inCorrectAnswers"

        db.collection("users_results").document("$userId").set(user)
    }

    override fun setPresenter(presenter: TestContract.Presenter) {
        this.presenter = presenter
    }
}