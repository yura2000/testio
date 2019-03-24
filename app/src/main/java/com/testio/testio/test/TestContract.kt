package com.testio.testio.test

import android.widget.RadioGroup
import com.google.common.collect.BiMap

interface TestContract {
    interface View {
        fun showData(value: String?, valueArray: BiMap<Int, String>?, shuffledKeys: List<Int>?)
        fun showGetDataError(resId: Int)
        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun getData(topicId: String?, count: Int?)
        fun loadData(value: String?, valueArray: BiMap<Int, String>?)
        fun isRadioButtonClicked(answerRg: RadioGroup): Boolean
    }
}