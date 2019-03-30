package com.testio.testio.test

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.google.common.collect.BiMap
import com.testio.testio.R

import kotlinx.android.synthetic.main.test_fragment.*


class TestFragment : Fragment(), TestContract.View {

    private var presenter: TestContract.Presenter? = null
    private var topicId: String? = null
    private var questionNumber: Int = 1
    private var TAG = "MyFRAGGG"
    private lateinit var callback: TestClickListener
    private var correctAnswers: Int? = 0
    private var inCorrectAnswers: Int? = 0
    private var userId: String? = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as TestClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        val userId = args?.getString("USER_ID")
        val topicId = args?.getString("TOPIC_ID")

        this.topicId = topicId
        this.userId = userId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //presenter?.getDocumentsCount(topicId)
        // Inflate the layout for this fragment
        return inflater.inflate(com.testio.testio.R.layout.test_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionNumber = 1
        presenter?.getDocumentsCount(topicId)
        presenter?.getData(topicId, questionNumber)
    }

    private fun incrementAnswer() {
        val selectedRadioButton: RadioButton = view!!.findViewById(answers_rb.checkedRadioButtonId)

        if (presenter?.isClickedCorrectRadioButton(selectedRadioButton.text.toString())!!)
            correctAnswers = correctAnswers?.inc()
        else
            inCorrectAnswers = inCorrectAnswers?.inc()
    }

    override fun showData(
        valueArray: BiMap<Int, String>?,
        shuffledKeys: List<Int>?
        ) {
        answer1_rb.text = valueArray!![shuffledKeys!![0]].toString()
        answer2_rb.text = valueArray[shuffledKeys[1]].toString()
        answer3_rb.text = valueArray[shuffledKeys[2]].toString()
        answer4_rb.text = valueArray[shuffledKeys[3]].toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        correctAnswers = 0
        inCorrectAnswers = 0
    }

    override fun showDocumentsCount(amountOfDocuments: Int) {
        if (amountOfDocuments > questionNumber) {
            next_btn.setOnClickListener {
                if (presenter?.isRadioButtonClicked(answers_rb)!!) {
                    incrementAnswer()
                    answers_rb.clearCheck()
                    next_btn.setBackgroundColor(Color.argb(255, 84, 177, 169))
                    questionNumber = questionNumber.inc()
                    presenter?.getData(topicId, questionNumber)
                    presenter?.getDocumentsCount(topicId)
                } else
                    next_btn.setBackgroundColor(Color.argb(255, 177, 84, 84))
            }
        } else if (amountOfDocuments == questionNumber) {
            next_btn.text = "Результати"
            next_btn.setOnClickListener {
                if (presenter?.isRadioButtonClicked(answers_rb)!!) {
                    incrementAnswer()
                    presenter?.saveUserData(correctAnswers, inCorrectAnswers, userId)
                    callback.onResultClicked(correctAnswers, inCorrectAnswers)
                } else {
                    next_btn.setBackgroundColor(Color.argb(255, 177, 84, 84))
                }
            }
        }
    }

    override fun showDocumentName(topicsName: String?) {
        titleTest_tv.text = topicsName
    }

    override fun showGetDataError() {

    }

    override fun setPresenter(presenter: TestContract.Presenter) {
        this.presenter = presenter
    }
}
