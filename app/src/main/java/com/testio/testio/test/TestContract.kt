package com.testio.testio.test

import android.widget.RadioGroup
import com.google.common.collect.BiMap

interface TestContract {
    interface View {
        var documentsCount: Int?
        fun showData(value: String?, valueArray: BiMap<Int, String>?, shuffledKeys: List<Int>?)
        fun showGetDataError(resId: Int)
        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun getData(topicId: String?, count: Int?)
        fun getDocumentsCount(topicId: String?): Int?
        fun loadData(value: String?, valueArray: BiMap<Int, String>?)
        fun isRadioButtonClicked(answerRg: RadioGroup): Boolean
        fun isClickedCorrectRadioButton(value: String?): Boolean
        fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?)
        var documentsCount: Int?
    }
}