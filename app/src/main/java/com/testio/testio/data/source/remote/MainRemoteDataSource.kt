package com.testio.testio.data.source.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.testio.testio.data.source.MainDataSource
import com.testio.testio.features.main.MainContract

class MainRemoteDataSource : MainDataSource {

    private var presenter: MainContract.Presenter? = null
    private var TAG = "MainRemoteDataSource"

    override fun getAccess(userId: String) {
        FirebaseFirestore.getInstance().collection("admins")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->

                if (document.exists()) {
                    val value = document.getBoolean("isAllowed")

                    presenter?.loadAccess(value!!)
                } else {
                    presenter?.loadAccess(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}