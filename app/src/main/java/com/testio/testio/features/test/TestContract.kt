package com.testio.testio.features.test

import android.widget.RadioGroup
import com.google.common.collect.BiMap

interface TestContract {
    interface View {
        fun showData(valueArray: BiMap<Int, String>?, shuffledKeys: List<Int>?)
        fun showGetDataError()
        fun showDocumentsCount(amountOfDocuments: Int)
        fun showDocumentName(topicsName: String?)
        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun getData(topicId: String?, questionNumber: Int?)
        fun getDocumentsCount(topicId: String?)
        fun loadDocumentsCount(amountOfDocuments: Int)
        fun loadData(valueArray: BiMap<Int, String>?)
        fun loadDocumentName(topicsName: String?)
        fun isRadioButtonClicked(answerRg: RadioGroup): Boolean
        fun isClickedCorrectRadioButton(value: String?): Boolean
        fun saveUserData(correctAnswers: Int?, inCorrectAnswers: Int?, userId: String?)
        fun dataLoadingError()
    }
}