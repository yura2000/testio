package com.testio.testio.features.main

interface MainContract {
    interface View {
        fun showImages(isAllowed: Boolean)
        fun setPresenter(presenter: Presenter)
    }
    interface Presenter {
        fun getAccess(userId: String)
        fun loadAccess(isAllowed: Boolean)
    }
}