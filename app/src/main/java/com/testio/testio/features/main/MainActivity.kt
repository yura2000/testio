package com.testio.testio.features.main

import android.content.ComponentCallbacks2
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.testio.testio.R
import com.testio.testio.data.source.remote.MainRemoteDataSource
import com.testio.testio.features.addtests.AddTestActivity
import com.testio.testio.features.learnmain.LearnMainActivity
import com.testio.testio.features.main.adapter.MainRecyclerView
import com.testio.testio.features.testmain.TestMainActivity
import com.testio.testio.features.topics.adapter.Menu
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private var presenter: MainContract.Presenter? = null

    private var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        MainPresenter(this, MainRemoteDataSource())

        val userId = intent.getStringExtra("USER_ID")
        this.userId = userId
        presenter?.getAccess(userId)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun showImages(isAllowed: Boolean) {

        val menu: List<Menu> = if (isAllowed)
            listOf(
                Menu(0, R.drawable.learn),
                Menu(1, R.drawable.test),
                Menu(2, R.drawable.addtest)
            )
        else
            listOf(
                Menu(0, R.drawable.learn),
                Menu(1, R.drawable.test),
                Menu(2, R.drawable.addtestnopermision)
            )


        val recyclerView = MainRecyclerView(menu)
        recyclerView.listener = (object : MainClickListener {
            override fun onImageClicked(menu: Menu) {
                if (isAllowed) {
                    when {
                        menu.id == 0 -> startLearnMainActivity()
                        menu.id == 1 -> startTestMainActivity()
                        menu.id == 2 -> startAddTestActivity()
                    }
                } else {
                    when {
                        menu.id == 0 -> startLearnMainActivity()
                        menu.id == 1 -> startTestMainActivity()
                    }
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
        intent.putExtra("USER_ID", userId)
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
