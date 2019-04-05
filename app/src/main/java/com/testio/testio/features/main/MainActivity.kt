package com.testio.testio.features.main

import android.content.Intent
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.testio.testio.R
import com.testio.testio.features.addtests.AddTestActivity
import com.testio.testio.features.learnmain.LearnMainActivity
import com.testio.testio.features.main.adapter.MainRecyclerView
import com.testio.testio.features.testmain.TestMainActivity
import com.testio.testio.features.topics.adapter.Menu
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private var mUserId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val userId = intent.getStringExtra("USER_ID")
        mUserId = userId
        showImages()
    }

    private fun showImages() {

        val menu: List<Menu> = listOf(
            Menu(0, R.drawable.learn),
            Menu(1, R.drawable.test),
            Menu(2, R.drawable.addtest)
        )

        val recyclerView = MainRecyclerView(menu)
        recyclerView.listener = (object : MainClickListener{
            override fun onImageClicked(menu: Menu) {
                when {
                    menu.id == 0 -> startLearnMainActivity()
                    menu.id == 1 -> startTestMainActivity()
                    menu.id == 2 -> startAddTestActivity()
                }
            }
        })
        val mainRecyclerView = main_recycler_view
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = recyclerView
    }

    private fun startLearnMainActivity() {
        val intent = Intent(this, LearnMainActivity::class.java)
        startActivity(intent)
    }

    private fun startTestMainActivity() {
        val intent = Intent(this, TestMainActivity::class.java)
        intent.putExtra("USER_ID", mUserId)
        startActivity(intent)
    }

    private fun startAddTestActivity() {
        val intent = Intent(this, AddTestActivity::class.java)
        startActivity(intent)
    }
}

interface MainClickListener {
    fun onImageClicked(menu: Menu)
}
