package com.testio.testio.data.source

import com.testio.testio.features.info.InfoContract

interface InfoDataSource {
    fun getTopicTitle(topicId: String?)
    fun getDocumentsCount(topicId: String?)
    fun getBackgroundImage(topicId: String?)
    fun setPresenter(presenter: InfoContract.Presenter)
}