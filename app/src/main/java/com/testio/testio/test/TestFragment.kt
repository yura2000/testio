package com.testio.testio.test

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.google.common.collect.BiMap

import kotlinx.android.synthetic.main.test_fragment.*


class TestFragment : Fragment(), TestContract.View {

    private var presenter: TestContract.Presenter? = null
    private var mTopicId: String? = null
    private var count: Int? = 1
    private var TAG = "MyFRAGGG"

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments
        val topicId = args?.getString("TOPIC_ID")

        Log.d(TAG, "$topicId, $count")
        mTopicId = topicId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.testio.testio.R.layout.test_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.getData(mTopicId, count)
    }

    override fun showData(value: String?, valueArray: BiMap<Int, String>?, shuffledKeys: List<Int>?) {

        titleTest_tv.text = value
        answer1_rb.text = valueArray!![shuffledKeys!![0]].toString()
        answer2_rb.text = valueArray[shuffledKeys[1]].toString()
        answer3_rb.text = valueArray[shuffledKeys[2]].toString()
        answer4_rb.text = valueArray[shuffledKeys[3]].toString()

        next_btn.setOnClickListener {
            if (presenter?.isRadioButtonClicked(answers_rb)!!) {
                val selectedId = answers_rb.checkedRadioButtonId
                val selectedRadioButton: RadioButton = view!!.findViewById(selectedId)
                if (presenter?.isClickedCorrectRadioButton(selectedRadioButton.text.toString())!!)
                    Log.d(TAG, "CORRECT")
                else
                    Log.d(TAG, "NOT CORRECT")
                count = count?.inc()
                presenter?.getData(mTopicId, count)
                answers_rb.clearCheck()
                next_btn.setBackgroundColor(Color.argb(255, 84, 177, 169))
            } else {
                next_btn.setBackgroundColor(Color.argb(255, 177, 84, 84))
            }
        }

    }

    override fun showGetDataError(resId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: TestContract.Presenter) {
        this.presenter = presenter
    }

    override fun onPause() {
        super.onPause()
        count = 1
    }
}
