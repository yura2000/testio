package com.testio.testio.features.results

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.testio.testio.R
import kotlinx.android.synthetic.main.results_fragment.*

class ResultsFragment : Fragment() {

    private var mCorrectAnswers: String? = ""
    private var mInCorrectAnswers: String? = ""
    private lateinit var callback: ResultsClickListener
    private var TAG = "MyResults"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as ResultsClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        val correctAnswers = args?.getString("CORRECT_ANSWERS")
        val inCorrectAnswers = args?.getString("INCORRECT_ANSWERS")

        mCorrectAnswers = correctAnswers
        mInCorrectAnswers = inCorrectAnswers
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.results_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textCorrect_tv.text = mCorrectAnswers
        Log.d(TAG, "$mCorrectAnswers, $mInCorrectAnswers")
        textInCorrect_tv.text = mInCorrectAnswers
        startTopics_btn.setOnClickListener { callback.onStartTopicsFromResultsClicked() }
    }
}
