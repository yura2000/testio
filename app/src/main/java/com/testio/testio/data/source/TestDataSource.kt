package com.testio.testio.data.source

import com.testio.testio.features.test.TestContract

interface TestDataSource {
    fun getData(topicId: Int?, count: Int?)
    fun getDocumentName(topicId: Int?, count: Int?)
    fun getDocumentsCount(topicId: Int?)
    fun setPresenter(presenter: TestContract.Presenter)
    fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?)
}