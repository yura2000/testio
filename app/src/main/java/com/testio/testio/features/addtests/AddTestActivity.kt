package com.testio.testio.features.addtests

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.add_test_activity.*
import android.widget.AdapterView.OnItemSelectedListener
import android.view.View
import android.widget.Toast
import com.testio.testio.models.Topic


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
                if ("Введіть тему" == chooseTopic_sp.selectedItem.toString()) {
                    enterTopic_et.visibility = View.VISIBLE
                    addTopic_btn.visibility = View.VISIBLE
                    addTopic_btn.setOnClickListener {
                        if (validateTopicForm())
                            viewModel.saveTopic(Topic(enterTopic_et.text.toString()))
                    }
                } else {
                    enterTopic_et.visibility = View.INVISIBLE
                    addTopic_btn.visibility = View.INVISIBLE
                }
                selectedItemId = position
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }
        }
        onSaveButtonPressed(viewModel)
    }

    private fun onChooseTopicSelected(viewModel: AddTestViewModel) {
        chooseTopic_sp.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                if ("Введіть тему" == chooseTopic_sp.selectedItem.toString()) {
                    enterTopic_et.visibility = View.VISIBLE
                    addTopic_btn.visibility = View.VISIBLE
                    addTopic_btn.setOnClickListener {
                        if (validateTopicForm())
                            viewModel.saveTopic(Topic(enterTopic_et.text.toString()))
                    }
                } else {
                    enterTopic_et.visibility = View.INVISIBLE
                    addTopic_btn.visibility = View.INVISIBLE
                }
                selectedItemId = position
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // your code here
            }
        }
    }

    private fun onSaveButtonPressed(viewModel: AddTestViewModel) {
        saveButton.setOnClickListener {
            if (validateForm()) {
                val questions: ArrayList<String> = arrayListOf()
                questions.apply {
                    Log.d(TAG, "MAMALAL")
                    add(enterCorrectAnswer_et.text.toString())
                    add(enterSecondAnswer_et.text.toString())
                    add(enterThirdAnswer_et.text.toString())
                    add(enterFourth_et.text.toString())
                }
                viewModel.saveData(selectedItemId + 1, enterQuestion_et.text.toString(), questions)

                enterQuestion_et.setText("")
                enterCorrectAnswer_et.setText("")
                enterSecondAnswer_et.setText("")
                enterThirdAnswer_et.setText("")
                enterFourth_et.setText("")
                Toast.makeText(this, "Тест додано. Дякуємо!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateTopicForm(): Boolean {
        var valid = true

        val topic = enterTopic_et.text.toString()
        if (TextUtils.isEmpty(topic)) {
            enterTopic_et.error = "Введіть тему!"
            valid = false
        } else {
            enterTopic_et.error = null
        }
        return valid
    }

    private fun validateForm(): Boolean {
        var valid = true

        val answer = enterCorrectAnswer_et.text.toString()
        if (TextUtils.isEmpty(answer)) {
            enterCorrectAnswer_et.error = "Введіть правильну відповідь!"
            valid = false
        } else {
            enterCorrectAnswer_et.error = null
        }

        val answer2 = enterSecondAnswer_et.text.toString()
        if (TextUtils.isEmpty(answer2)) {
            enterSecondAnswer_et.error = "Введіть тему!"
            valid = false
        } else {
            enterSecondAnswer_et.error = null
        }

        val answer3 = enterThirdAnswer_et.text.toString()
        if (TextUtils.isEmpty(answer3)) {
            enterThirdAnswer_et.error = "Введіть тему!"
            valid = false
        } else {
            enterThirdAnswer_et.error = null
        }

        val answer4 = enterFourth_et.text.toString()
        if (TextUtils.isEmpty(answer4)) {
            enterFourth_et.error = "Введіть тему!"
            valid = false
        } else {
            enterFourth_et.error = null
        }

        return valid
    }
}
