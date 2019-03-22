package com.testio.testio.info

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.testio.testio.R
import kotlinx.android.synthetic.main.info_fragment.*

class InfoFragment : Fragment() {

    private lateinit var callback: InfoClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as InfoClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        infoButton_btn.setOnClickListener { callback.onStartClicked() }
    }
}
