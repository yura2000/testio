package com.testio.testio.features.main

import com.testio.testio.data.source.MainDataSource

class MainPresenter(mainView: MainContract.View, mainData: MainDataSource) : MainContract.Presenter {
    private var mainData: MainDataSource? = null
    private var mainView: MainContract.View? = null

    init {
        this.mainView = mainView
        this.mainData = mainData

        this.mainView?.setPresenter(this)
        this.mainData?.setPresenter(this)
    }

    override fun getAccess(userId: String) {
        mainData?.getAccess(userId)
    }

    override fun loadAccess(isAllowed: Boolean) {
        mainView?.showImages(isAllowed)
    }
}