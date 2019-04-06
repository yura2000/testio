package com.testio.testio.features.addtests

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.testio.testio.R
import kotlinx.android.synthetic.main.add_test_activity.*
import android.widget.AdapterView.OnItemSelectedListener
import android.view.View


class AddTestActivity : AppCompatActivity() {

    private var addTestViewModel = AddTestViewModel()
    private var selectedItemId: Int = 0
    private var TAG = "MyMVVM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.testio.testio.R.layout.add_test_activity)

        val viewModel = ViewModelProviders
            .of(this)
            .get(AddTestViewModel::class.java)

        viewModel.loadTopics()

        viewModel.topics.observe(this,
            Observer<ArrayList<String>> {
                it?.add("Введіть тему")
                ArrayAdapter(this, android.R.layout.simple_spinner_item, it)
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        chooseTopic_sp.adapter = adapter
                    }
            })
        chooseTopic_sp.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                if ("Введіть тему" == chooseTopic_sp.selectedItem.toString())
                    enterTopic_et.visibility = View.VISIBLE
                else
                    enterTopic_et.visibility = View.INVISIBLE
                selectedItemId = position
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }
        }
        saveButton.setOnClickListener {
            val questions: ArrayList<String> = arrayListOf()
            questions.apply {
                add(enterCorrectAnswer_et.text.toString())
                add(enterSecondAnswer_et.text.toString())
                add(enterThirdAnswer_et.text.toString())
                add(enterFourth_et.text.toString())
            }
            viewModel.saveData(selectedItemId + 1, enterQuestion_et.text.toString(), questions)
        }
    }
}
