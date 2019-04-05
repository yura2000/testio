package com.testio.testio.repos

import com.testio.testio.models.Info

class InfoRepo {
    private val remoteDataSource = InfoRepoRemoteDataSource()

    fun getInfo(onInfoReadyCallback: OnInfoReadyCallback) {
        remoteDataSource.getInfo(object : OnInfoRemoteReadyCallback {
            override fun onRemoteDataReady(data: Info) {
                onInfoReadyCallback.onDataReady(data)
            }
        })
    }
}

interface OnInfoReadyCallback {
    fun onDataReady(data: Info)
}