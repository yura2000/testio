package com.testio.testio.data.source.remote

import android.util.Log
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.firebase.firestore.FirebaseFirestore
import com.testio.testio.data.source.TestDataSource
import com.testio.testio.features.test.TestContract


class TestRemoteDataSource : TestDataSource {

    private var presenter: TestContract.Presenter? = null
    private var TAG = "MyData"
    private var database: String = "main_topics"

    override fun getData(topicId: Int?, count: Int?) {
        val db = FirebaseFirestore.getInstance()

        val valueArray: BiMap<Int, String>? = HashBiMap.create()

        db.collection("$database/topic$topicId/questions/question$count/answers")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val test = document.data["id"].toString()

                        valueArray?.put(test.toInt(), document.data["title"].toString())
                    }
                    presenter?.loadData(valueArray)
                } else {
                    presenter?.dataLoadingError()
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }

    override fun getDocumentName(topicId: Int?, count: Int?) {
        val db = FirebaseFirestore.getInstance()

        db.collection("$database/topic$topicId/questions")
            .document("question$count")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val value = document.getString("title")
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

    override fun getDocumentsCount(topicId: Int?) {
        val db = FirebaseFirestore.getInstance()
        var amountOfDocuments: Int

        db.collection("$database/topic$topicId/questions")
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