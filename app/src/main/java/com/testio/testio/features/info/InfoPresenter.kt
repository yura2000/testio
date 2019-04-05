package com.testio.testio.features.info

import com.testio.testio.data.source.InfoDataSource

class InfoPresenter(infoView: InfoContract.View, infoSource: InfoDataSource) :
    InfoContract.Presenter {

    private var infoRemoteDataSource: InfoDataSource? = null
    private var infoView: InfoContract.View? = null

    init {
        this.infoView = infoView
        this.infoRemoteDataSource = infoSource

        this.infoView?.setPresenter(this)
        this.infoRemoteDataSource?.setPresenter(this)
    }

    override fun getTopicName(topicId: String?) {
        infoRemoteDataSource?.getTopicTitle(topicId)
    }

    override fun getBackgroundImage(topicId: String?) {
        infoRemoteDataSource?.getBackgroundImage(topicId)
    }

    override fun getNumberOfQuestions(topicId: String?) {
        infoRemoteDataSource?.getDocumentsCount(topicId)
    }

    override fun loadTopicName(topicTitle: String?) {
        infoView?.showTopicName(topicTitle)
    }

    override fun loadBackgroundImage(topicImage: String?) {
        infoView?.showBackgroundImage(topicImage)
    }

    override fun loadNumberOfQuestions(numOfQuestions: Int?) {
        infoView?.showNumberOfQuestions(numOfQuestions)
    }

    override fun dataLoadingError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}