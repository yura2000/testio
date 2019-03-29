package com.testio.testio.test

import android.util.Log
import android.widget.RadioGroup
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.testio.testio.data.source.TestDataSource

class TestPresenter(testView: TestContract.View, testData: TestDataSource) : TestContract.Presenter {

    private var mTestRemoteDataSource: TestDataSource? = null
    private var mTestView: TestContract.View? = null
    private var documentCount: Int = 0
    private var TAG = "MyPresenter"
    private val mValueArray: BiMap<Int, String>? = HashBiMap.create()
    private var topicsName: String? = null

    init {
        mTestView = testView
        mTestRemoteDataSource = testData

        mTestView?.setPresenter(this)
        mTestRemoteDataSource?.setPresenter(this)
    }

    override fun getData(topicId: String?, count: Int?) {
        mTestRemoteDataSource?.getDocumentName(topicId, count)
        mTestRemoteDataSource?.getData(topicId, count)
    }

    override fun getDocumentsCount(topicId: String?) {
        mTestRemoteDataSource?.getDocumentsCount(topicId)
    }

    override fun loadData(valueArray: BiMap<Int, String>?) {
        val shuffledKeys = valueArray?.keys?.shuffled()

        mValueArray?.put(shuffledKeys!![0], valueArray[shuffledKeys[0]])
        mValueArray?.put(shuffledKeys!![1], valueArray[shuffledKeys[1]])
        mValueArray?.put(shuffledKeys!![2], valueArray[shuffledKeys[2]])
        mValueArray?.put(shuffledKeys!![3], valueArray[shuffledKeys[3]])

        Log.d(TAG, "DOCUMMMAA $topicsName")
        mTestView?.showData(topicsName, mValueArray, shuffledKeys, documentCount)
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
        this.topicsName = topicsName
        Log.d(TAG, "Here must be name of the topic: $topicsName")
    }

    override fun loadDocumentsCount(documentCount: Int) {
        this.documentCount = documentCount
        Log.d(TAG, "DOCUM $documentCount")
    }

    override fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?) {
        mTestRemoteDataSource?.saveUserData(correctAnswers, inCorrectAnswers, userId)
    }
}