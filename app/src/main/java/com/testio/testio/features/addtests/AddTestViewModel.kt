package com.testio.testio.features.addtests

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.testio.testio.repos.AddTestRepo
import com.testio.testio.repos.OnTopicsReadyCallback

class AddTestViewModel : ViewModel() {

    var topics = MutableLiveData<ArrayList<String>>()
    private var repository: AddTestRepo = AddTestRepo()

    fun loadTopics() {
        repository.getTopics(object : OnTopicsReadyCallback {
            override fun onDataReady(data: ArrayList<String>) {
                topics.value = data
            }
        })
    }

    fun saveData(topicId: Int, questionTitle: String, answers: ArrayList<String>) {
        repository.saveData(topicId, questionTitle, answers)
    }
}