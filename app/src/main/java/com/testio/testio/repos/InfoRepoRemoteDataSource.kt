package com.testio.testio.repos

import com.testio.testio.models.Info
import android.media.Rating
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.CollectionReference




class InfoRepoRemoteDataSource {

    private val restaurants: CollectionReference? = null

    fun getInfo(onInfoReadyCallback: OnInfoRemoteReadyCallback) {
        val info = Info("TEst", "5", "Test")
        onInfoReadyCallback.onRemoteDataReady(info)
    }


//    private fun query(id: String): Query {
//        return restaurants!!.document(id)
//            .collection("ratings")
//            .orderBy("timestamp", Query.Direction.DESCENDING)
//            .limit(50)
//    }
//
//    fun ratings(id: String): QueryLiveData<Rating> {
//        return QueryLiveData(query(id), Rating::class.java)
//    }
}

interface OnInfoRemoteReadyCallback {
    fun onRemoteDataReady(data: Info)
}