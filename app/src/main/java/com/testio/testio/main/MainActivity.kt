package com.testio.testio.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.testio.testio.R
import com.testio.testio.data.Item
import com.testio.testio.data.source.remote.TopicsRemoteDataSource
import com.testio.testio.info.InfoClickListener
import com.testio.testio.info.InfoFragment
import com.testio.testio.topics.TopicsClickListener
import com.testio.testio.topics.TopicsFragment
import com.testio.testio.topics.TopicsPresenter

class MainActivity : AppCompatActivity(), MainScreenContract.View, TopicsClickListener, InfoClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        startTopicsFragment()
    }

    override fun startTopicsFragment() {
        val fragment = TopicsFragment()

        val repository = TopicsRemoteDataSource()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frag, fragment)
            .commit()

        val presenter = TopicsPresenter(fragment, repository)
    }

    override fun onTopicsClicked(item: Item?) {
        startInfoFragment()
    }

    override fun startInfoFragment() {
        val fragment = InfoFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frag, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onStartClicked() {
        startTestFragment()
    }

    override fun startTestFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
