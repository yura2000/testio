package com.testio.testio.features.info

interface InfoContract {
    interface View {
        fun showTopicName(topicTitle: String?)
        fun showBackgroundImage(topicImage: String?)
        fun showNumberOfQuestions(numOfQuestions: Int?)
        fun showGetDataError()
        fun setPresenter(presenter: Presenter)
    }
    interface Presenter {
        fun getTopicName(topicId: String?)
        fun getBackgroundImage(topicId: String?)
        fun getNumberOfQuestions(topicId: String?)
        fun loadTopicName(topicTitle: String?)
        fun loadBackgroundImage(topicImage: String?)
        fun loadNumberOfQuestions(numOfQuestions: Int?)
        fun dataLoadingError()
    }
}