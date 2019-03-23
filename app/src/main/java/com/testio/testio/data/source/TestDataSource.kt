package com.testio.testio.data.source

import com.testio.testio.test.TestContract

interface TestDataSource {
    fun getData(topicId: String?, count: Int?)
    fun setPresenter(presenter: TestContract.Presenter)
}