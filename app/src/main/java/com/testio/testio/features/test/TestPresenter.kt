package com.testio.testio.features.test

import android.widget.RadioGroup
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.testio.testio.data.source.TestDataSource

class TestPresenter(testView: TestContract.View, testData: TestDataSource) :
    TestContract.Presenter {

    private var testRemoteDataSource: TestDataSource? = null
    private var testView: TestContract.View? = null
    private var TAG = "MyPresenter"
    private val mValueArray: BiMap<Int, String>? = HashBiMap.create()

    init {
        this.testView = testView
        testRemoteDataSource = testData

        this.testView?.setPresenter(this)
        testRemoteDataSource?.setPresenter(this)
    }

    override fun getData(topicId: Int?, questionNumber: Int?) {
        testRemoteDataSource?.getDocumentName(topicId, questionNumber)
        testRemoteDataSource?.getData(topicId, questionNumber)
    }

    override fun getDocumentsCount(topicId: Int?) {
        testRemoteDataSource?.getDocumentsCount(topicId)
    }

    override fun loadData(valueArray: BiMap<Int, String>?) {
        val shuffledKeys = valueArray?.keys?.shuffled()

        mValueArray?.put(shuffledKeys!![0], valueArray[shuffledKeys[0]])
        mValueArray?.put(shuffledKeys!![1], valueArray[shuffledKeys[1]])
        mValueArray?.put(shuffledKeys!![2], valueArray[shuffledKeys[2]])
        mValueArray?.put(shuffledKeys!![3], valueArray[shuffledKeys[3]])

        testView?.showData(mValueArray, shuffledKeys)
    }

    override fun isRadioButtonClicked(answerRg: RadioGroup): Boolean {
        if (answerRg.checkedRadioButtonId == -1)
            return false
        return true
    }

    override fun isClickedCorrectRadioButton(value: String?): Boolean {
        if (mValueArray?.inverse()?.get(value) == 1)
            return true
        return false
    }

    override fun loadDocumentName(topicsName: String?) {
        testView?.showDocumentName(topicsName)
    }

    override fun loadDocumentsCount(amountOfDocuments: Int) {
        testView?.showDocumentsCount(amountOfDocuments)
    }

    override fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?) {
        testRemoteDataSource?.saveUserData(correctAnswers, inCorrectAnswers, userId)
    }

    override fun dataLoadingError() {
        testView?.showGetDataError()
    }
}