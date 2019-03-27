package com.testio.testio.test

import android.util.Log
import android.widget.RadioGroup
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.testio.testio.data.source.TestDataSource

class TestPresenter(testView: TestContract.View, testData: TestDataSource)
    : TestContract.Presenter{

    private var mTestRemoteDataSource: TestDataSource? = null
    private var mTestView: TestContract.View? = null
    private var TAG = "MyBitch"
    private val mValueArray: BiMap<Int, String>? = HashBiMap.create()

    init {
        mTestView = testView
        mTestRemoteDataSource = testData

        mTestView?.setPresenter(this)
        mTestRemoteDataSource?.setPresenter(this)
    }

    override fun getData(topicId: String?, count: Int?) {
        mTestRemoteDataSource?.getData(topicId, count)
    }

    override fun loadData(value: String?, valueArray: BiMap<Int, String>?) {
        val shuffledKeys = valueArray?.keys?.shuffled()

        mValueArray?.put(shuffledKeys!![0], valueArray[shuffledKeys[0]])
        mValueArray?.put(shuffledKeys!![1], valueArray[shuffledKeys[1]])
        mValueArray?.put(shuffledKeys!![2], valueArray[shuffledKeys[2]])
        mValueArray?.put(shuffledKeys!![3], valueArray[shuffledKeys[3]])

        mTestView?.showData(value, mValueArray, shuffledKeys)
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

    override var documentsCount: Int? = 0

    override fun getDocumentsCount(topicId: String?) : Int? {
        mTestRemoteDataSource?.getDocumentsCount(topicId)
        Log.d(TAG, "$documentsCount")
        return documentsCount
    }

    override fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?) {
        mTestRemoteDataSource?.saveUserData(correctAnswers, inCorrectAnswers, userId)
    }
}