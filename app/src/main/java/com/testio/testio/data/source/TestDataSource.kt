package com.testio.testio.data.source

import com.testio.testio.test.TestContract

interface TestDataSource {
    fun getData(topicId: String?, count: Int?)
    fun getDocumentsCount(topicId: String?) : Int?
    fun setPresenter(presenter: TestContract.Presenter)
    fun saveUserData(correctAnswers: Int?, unCorrectAnswers: Int?, userId: String?)
}