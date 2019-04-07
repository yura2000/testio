package com.testio.testio.repos

import com.testio.testio.data.source.remote.AddTestRemoteDataSource
import com.testio.testio.data.source.remote.OnRepoRemoteReadyCallback
import com.testio.testio.models.Topic

class AddTestRepo {
    private val remoteDataSource = AddTestRemoteDataSource()

    fun getTopics(onTopicsReadyCallback: OnTopicsReadyCallback) {
        remoteDataSource.getTopics(object : OnRepoRemoteReadyCallback {
            override fun onRemoteDataReady(data: ArrayList<String>) {
                onTopicsReadyCallback.onDataReady(data)
            }
        })
    }

    fun saveData(topicId: Int, questionTitle: String, answers: ArrayList<String>) {
        remoteDataSource.saveData(topicId, questionTitle, answers)
    }

    fun saveTopic(topic: Topic) {
        remoteDataSource.saveTopic(topic)
    }
}

interface OnTopicsReadyCallback {
    fun onDataReady(data: ArrayList<String>)
}