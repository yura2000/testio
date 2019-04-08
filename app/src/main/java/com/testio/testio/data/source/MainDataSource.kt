package com.testio.testio.data.source

import com.testio.testio.features.main.MainContract

interface MainDataSource {
    fun getAccess(userId: String)
    fun setPresenter(presenter: MainContract.Presenter)
}