package com.testio.testio.features.info

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.testio.testio.models.Info
import com.testio.testio.repos.InfoRepo
import com.testio.testio.repos.OnInfoReadyCallback

class InfoViewModel(application: Application) : AndroidViewModel(application) {
    var infoRepo = InfoRepo()
    val isLoading = ObservableField(false)

    var info = MutableLiveData<Info>()

    fun loadInfo() {
        isLoading.set(true)
        infoRepo.getInfo(object : OnInfoReadyCallback {
            override fun onDataReady(data: Info) {
                isLoading.set(false)
                info.value = data
            }
        })
    }
}