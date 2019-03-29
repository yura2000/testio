package com.testio.testio.data.source

import com.testio.testio.test.TestContract

interface TestDataSource {
    fun getData(topicId: String?, count: Int?)
    fun getDocumentName(topicId: String?, count: Int?)
    fun getDocumentsCount(topicId: String?)
    fun setPresenter(presenter: TestContract.Presenter)
    fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?)
}