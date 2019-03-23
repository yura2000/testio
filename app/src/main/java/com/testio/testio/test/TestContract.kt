package com.testio.testio.test

interface TestContract {
    interface View {
        fun showData(value: String?, valueArray: MutableList<String>?)
        fun showGetDataError(resId: Int)
        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun getData(topicId: String?, count: Int?)
        fun loadData(value: String?, valueArray: MutableList<String>?)
    }
}