package com.testio.testio.test

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerOptions

import com.testio.testio.R
import com.testio.testio.data.Item
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
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter?.getData(mTopicId, count)
    }

    override fun showData(value: String?, valueArray: MutableList<String>?) {

        titleTest_tv.text = value
        answerOne_rb.text = valueArray?.get(0)
        answerTwo_rb.text = valueArray?.get(1)
        answerThree_rb.text = valueArray?.get(2)
        answerFour_rb.text = valueArray?.get(3)

        next_btn.setOnClickListener {
            count = count?.inc()
            presenter?.getData(mTopicId, count)
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
