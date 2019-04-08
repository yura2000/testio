package com.testio.testio.data.source.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.testio.testio.models.Topic


class AddTestRemoteDataSource {

    private var TAG = "MyData"
    private var database: String = "main_topics"

    fun getTopics(onTopicsReadyCall: OnRepoRemoteReadyCallback) {
        val query = FirebaseFirestore.getInstance()
            .collection(database)
            .orderBy("id")

        query.addSnapshotListener { snapshot, _ ->
            val valueArray = ArrayList<String>()
            for (document in snapshot!!.documents) {
                document.getString("title")?.let { valueArray.add(it) }
            }
            onTopicsReadyCall.onRemoteDataReady(valueArray)
        }
    }

    fun saveData(topicId: Int, questionTitle: String, answers: ArrayList<String>) {
        val db = FirebaseFirestore.getInstance()

        db.collection(database)
            .document("topic$topicId")
            .collection("questions")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val numberOfDocuments = task.result!!.size()
                    val question = HashMap<String, Any>()
                    question["id"] = numberOfDocuments + 1
                    question["title"] = questionTitle
                    db.document("$database/topic$topicId")
                        .collection("questions")
                        .document("question${numberOfDocuments + 1}")
                        .set(question)

                    var count = 1
                    for (answer in answers) {
                        val answersOptions = HashMap<String, Any>()
                        answersOptions["id"] = count
                        answersOptions["title"] = answer
                        db.document("$database/topic$topicId")
                            .collection("questions")
                            .document("question${numberOfDocuments + 1}")
                            .collection("answers")
                            .document("ans$count")
                            .set(answersOptions)
                        count = count.inc()
                        Log.d(TAG, "HERE$topicId")
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }

            }
    }

    fun saveTopic(topic: Topic) {
        val db = FirebaseFirestore.getInstance()

        db.collection(database)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val numberOfDocuments = task.result!!.size()

                    val options = HashMap<String, Any>()
                    options["id"] = numberOfDocuments+1
                    options["title"] = topic.title

                    db.collection(database)
                        .document("topic${numberOfDocuments+1}")
                        .set(options)

                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }

            }

    }
}

interface OnRepoRemoteReadyCallback {
    fun onRemoteDataReady(data: ArrayList<String>)
}