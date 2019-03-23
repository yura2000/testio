package com.testio.testio.main

import com.testio.testio.data.Item

interface MainScreenContract {
    interface View {
        fun startTopicsFragment()
        fun startInfoFragment(item: Item?)
        fun startTestFragment()
    }
}