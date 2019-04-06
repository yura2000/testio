package com.testio.testio.data.source.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.testio.testio.data.source.InfoDataSource
import com.testio.testio.features.info.InfoContract

class InfoRemoteDataSource : InfoDataSource {

    private var presenter: InfoContract.Presenter? = null
    private var TAG = "MyData"

    override fun getTopicTitle(topicId: String?) {
        val db = FirebaseFirestore.getInstance()

        db.document("topics/topic$topicId")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val value = document.getString("title")

                    presenter?.loadTopicName(value)
                } else {
                    presenter?.dataLoadingError()
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                presenter?.dataLoadingError()
                Log.d(TAG, "get failed with ", exception)
            }    }

    override fun getDocumentsCount(topicId: String?) {
        val db = FirebaseFirestore.getInstance()
        var amountOfDocuments: Int

        db.collection("topics/topic$topicId/questions")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    amountOfDocuments = task.result!!.size()
                    presenter?.loadNumberOfQuestions(amountOfDocuments)
                } else {
                    presenter?.dataLoadingError()
                    Log.w(TAG, "Error getting documents.", task.exception)
                }

            }
    }

    override fun getBackgroundImage(topicId: String?) {
        val db = FirebaseFirestore.getInstance()

        db.document("topics/topic$topicId")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val value = document.getString("imageBackground")
                    presenter?.loadBackgroundImage(value)
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

    override fun setPresenter(presenter: InfoContract.Presenter) {
        this.presenter = presenter
    }
}