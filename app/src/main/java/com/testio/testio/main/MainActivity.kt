package com.testio.testio.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.testio.testio.R
import com.testio.testio.data.Item
import com.testio.testio.data.source.remote.TestRemoteDataSource
import com.testio.testio.data.source.remote.TopicsRemoteDataSource
import com.testio.testio.info.InfoClickListener
import com.testio.testio.info.InfoFragment
import com.testio.testio.test.TestFragment
import com.testio.testio.test.TestPresenter
import com.testio.testio.topics.TopicsClickListener
import com.testio.testio.topics.TopicsFragment
import com.testio.testio.topics.TopicsPresenter

class MainActivity : AppCompatActivity(), MainScreenContract.View, TopicsClickListener, InfoClickListener {

    private val topicsFragment = TopicsFragment()
    private val infoFragment = InfoFragment()
    private val testFragment = TestFragment()
    private val TAG = "MyAAA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        startTopicsFragment()
    }

    override fun startTopicsFragment() {
        val repository = TopicsRemoteDataSource()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frag, topicsFragment)
            .commit()

        val presenter = TopicsPresenter(topicsFragment, repository)
    }

    override fun onTopicsClicked(item: Item?) {
        startInfoFragment(item)
    }

    override fun startInfoFragment(item: Item?) {
        val arg = Bundle()
        arg.putString("TOPIC_TITLE", item?.title)

        infoFragment.arguments = arg

        arg.putString("TOPIC_ID", item?.id)
        testFragment.arguments = arg

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frag, infoFragment)
            .show(infoFragment)
            .hide(topicsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onStartClicked() {
        startTestFragment()
    }

    override fun startTestFragment() {
        val repository = TestRemoteDataSource()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frag, testFragment)
            .show(testFragment)
            .hide(infoFragment)
            .addToBackStack(null)
            .commit()

        val presenter = TestPresenter(testFragment, repository)
    }
}
