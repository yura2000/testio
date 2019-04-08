package com.testio.testio.features.info

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.common.api.internal.LifecycleFragment
import com.squareup.picasso.Picasso
import com.testio.testio.R
import kotlinx.android.synthetic.main.info_fragment.*


class InfoFragment : Fragment(), InfoContract.View {

    private lateinit var callback: InfoClickListener
    private var topicId: String? = null
    private val TAG = "MyActivity"
    private var presenter: InfoContract.Presenter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as InfoClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        val topicId = args?.getInt("TOPIC_ID")

        this.topicId = topicId.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(R.mipmap.student).into(userImage_img)

        presenter?.getTopicName(topicId)
        presenter?.getBackgroundImage(topicId)
        presenter?.getNumberOfQuestions(topicId)

    }

    override fun showTopicName(topicTitle: String?) {
        infoTitle_tv.text = topicTitle
    }

    override fun showBackgroundImage(topicImage: String?) {
        Picasso.get().load(topicImage).error(R.mipmap.error_lg).placeholder(R.drawable.progress_animation).into(infoImage_img)
    }

    override fun showNumberOfQuestions(numOfQuestions: Int?) {
        infoQuestions_tv.text = numOfQuestions.toString()

        if (numOfQuestions.toString() != "0")
            infoButton_btn.setOnClickListener { callback.onStartClicked() }
        else {
            infoButton_btn.setBackgroundColor(Color.argb(255, 176, 0, 32))
            infoButton_btn.setTextColor(Color.argb(255, 237, 240, 242))
        }
    }

    override fun showGetDataError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: InfoContract.Presenter) {
        this.presenter = presenter
    }
}
