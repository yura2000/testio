package com.testio.testio.repos

import com.testio.testio.data.source.remote.AddTestRemoteDataSource
import com.testio.testio.data.source.remote.OnRepoRemoteReadyCallback

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
}

interface OnTopicsReadyCallback {
    fun onDataReady(data: ArrayList<String>)
}