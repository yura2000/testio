package com.testio.testio.test

import android.widget.RadioGroup
import com.google.common.collect.BiMap

interface TestContract {
    interface View {
        fun showData(topicsName: String?, valueArray: BiMap<Int, String>?, shuffledKeys: List<Int>?, documentCount: Int)
        fun showGetDataError(resId: Int)
        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun getData(topicId: String?, count: Int?)
        fun getDocumentsCount(topicId: String?)
        fun loadDocumentsCount(documentCount: Int)
        fun loadData(valueArray: BiMap<Int, String>?)
        fun loadDocumentName(topicsName: String?)
        fun isRadioButtonClicked(answerRg: RadioGroup): Boolean
        fun isClickedCorrectRadioButton(value: String?): Boolean
        fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?)
    }
}