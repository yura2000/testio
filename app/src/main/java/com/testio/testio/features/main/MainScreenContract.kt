package com.testio.testio.features.main

import com.testio.testio.data.models.Item

interface MainScreenContract {
    interface View {
        fun startTopicsFragment()
        fun startInfoFragment(item: Item?, mUserId: String)
        fun startTestFragment()
        fun startResultsFragment(correctAnswers: Int?, inCorrectAnswers: Int?)
    }
}