package com.testio.testio.test

import android.util.Log
import com.testio.testio.data.source.TestDataSource

class TestPresenter(testView: TestContract.View, testData: TestDataSource)
    : TestContract.Presenter{

    private var mTestRemoteDataSource: TestDataSource? = null
    private var mTestView: TestContract.View? = null
    private var TAG = "MyBitch"

    init {
        mTestView = testView
        mTestRemoteDataSource = testData

        mTestView?.setPresenter(this)
        mTestRemoteDataSource?.setPresenter(this)
    }

    override fun getData(topicId: String?, count: Int?) {
        mTestRemoteDataSource?.getData(topicId, count)
    }

    override fun loadData(value: String?, valueArray: MutableList<String>?) {
        mTestView?.showData(value, valueArray)
    }
}