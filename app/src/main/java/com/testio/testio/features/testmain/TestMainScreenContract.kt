package com.testio.testio.features.testmain

import com.testio.testio.models.Item

interface TestMainScreenContract {
    interface View {
        fun startTopicsFragment()
        fun startInfoFragment(item: Item?, mUserId: String)
        fun startTestFragment()
        fun startResultsFragment(correctAnswers: Int?, inCorrectAnswers: Int?)
    }
}