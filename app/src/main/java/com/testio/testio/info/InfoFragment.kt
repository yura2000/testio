package com.testio.testio.info

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.testio.testio.R
import kotlinx.android.synthetic.main.info_fragment.*

class InfoFragment : Fragment() {

    private lateinit var callback: InfoClickListener
    private var mTopicTitle: String? = null
    private val TAG = "MyActivity"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as InfoClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        val topicTitle = args?.getString("TOPIC_TITLE")

        mTopicTitle = topicTitle
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
        infoTitle_tv.text = mTopicTitle
        infoButton_btn.setOnClickListener { callback.onStartClicked() }
    }
}
