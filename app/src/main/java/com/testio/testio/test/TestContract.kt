package com.testio.testio.test

interface TestContract {
    interface View {
        fun showData()
        fun showGetDataError(resId: Int)
    }

    interface Presenter {
        fun getData()
        fun loadData()

    }
}