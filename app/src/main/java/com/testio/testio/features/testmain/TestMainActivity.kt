package com.testio.testio.features.testmain

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.testio.testio.data.models.Item
import com.testio.testio.data.source.remote.InfoRemoteDataSource
import com.testio.testio.data.source.remote.TestRemoteDataSource
import com.testio.testio.data.source.remote.TopicsRemoteDataSource
import com.testio.testio.features.info.InfoClickListener
import com.testio.testio.features.info.InfoFragment
import com.testio.testio.features.results.ResultsClickListener
import com.testio.testio.features.results.ResultsFragment
import com.testio.testio.features.test.TestClickListener
import com.testio.testio.features.test.TestFragment
import com.testio.testio.features.test.TestPresenter
import com.testio.testio.features.topics.TopicsClickListener
import com.testio.testio.features.topics.TopicsFragment
import com.testio.testio.features.topics.TopicsPresenter
import android.support.v4.app.FragmentManager
import com.testio.testio.features.info.InfoPresenter


class TestMainActivity : AppCompatActivity(), TestMainScreenContract.View,
    TopicsClickListener, InfoClickListener, TestClickListener, ResultsClickListener {

    private val topicsFragment = TopicsFragment()
    private val infoFragment = InfoFragment()
    private val testFragment = TestFragment()
    private val resultsFragment = ResultsFragment()
    private var mUserId: String = ""
    private val TAG = "MyAAA"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.testio.testio.R.layout.test_main_activity)
        val userId = intent.getStringExtra("USER_ID")

        mUserId = userId

        startTopicsFragment()
    }

    override fun startTopicsFragment() {
        val repository = TopicsRemoteDataSource()

        if (supportFragmentManager.findFragmentById(com.testio.testio.R.id.main_frag) is TopicsFragment) {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(com.testio.testio.R.anim.enter_from_right, com.testio.testio.R.anim.exit_to_right)
                .show(topicsFragment)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(com.testio.testio.R.anim.enter_from_right, com.testio.testio.R.anim.exit_to_right)
                .replace(com.testio.testio.R.id.main_frag, topicsFragment)
                .show(topicsFragment)
                .commit()
        }

        val presenter = TopicsPresenter(topicsFragment, repository)
    }

    override fun onTopicsClicked(item: Item?) {
        startInfoFragment(item, mUserId)
    }

    override fun startInfoFragment(item: Item?, mUserId: String) {
        val repository = InfoRemoteDataSource()

        val arg = Bundle()

        arg.putString("TOPIC_ID", item?.id)

        infoFragment.arguments = arg

        arg.putString("TOPIC_ID", item?.id)
        arg.putString("USER_ID", mUserId)

        testFragment.arguments = arg

        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(com.testio.testio.R.anim.enter_from_right, com.testio.testio.R.anim.exit_to_right)
            .add(com.testio.testio.R.id.main_frag, infoFragment)
            .show(infoFragment)
            .hide(topicsFragment)
            .addToBackStack(null)
            .commit()

        val presenter = InfoPresenter(infoFragment, repository)
    }

    override fun onStartClicked() {
        startTestFragment()
    }

    override fun startTestFragment() {
        val repository = TestRemoteDataSource()

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(com.testio.testio.R.anim.enter_from_right, com.testio.testio.R.anim.exit_to_right)
            .add(com.testio.testio.R.id.main_frag, testFragment)
            .show(testFragment)
            .hide(infoFragment)
            .addToBackStack(null)
            .commit()

        val presenter = TestPresenter(testFragment, repository)
    }

    override fun onResultClicked(correctAnswers: Int?, unCorrectAnswers: Int?) {
        startResultsFragment(correctAnswers, unCorrectAnswers)
    }

    override fun startResultsFragment(correctAnswers: Int?, inCorrectAnswers: Int?) {
        val args = Bundle()

        args.putString("CORRECT_ANSWERS", correctAnswers.toString())
        args.putString("INCORRECT_ANSWERS", inCorrectAnswers.toString())

        resultsFragment.arguments = args

        supportFragmentManager
            .beginTransaction()
            .remove(testFragment)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(com.testio.testio.R.anim.enter_from_right, com.testio.testio.R.anim.exit_to_right)
            .add(com.testio.testio.R.id.main_frag, resultsFragment)
            .show(resultsFragment)
            .hide(testFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onStartTopicsFromResultsClicked() {
        val args = Bundle()

        args.remove("CORRECT_ANSWERS")
        args.remove("INCORRECT_ANSWERS")

        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager
            .beginTransaction()
            .remove(infoFragment)
            .remove(resultsFragment)
            .commit()

        startTopicsFragment()
    }
}
